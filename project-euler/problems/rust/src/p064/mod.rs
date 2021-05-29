#[derive(Copy, Clone, Debug)]
struct Term {
    front: u64,
    numerator: u64,
    bottom: u64,
    s: u64,
    sqrt_floor: u64,
}

impl Term {
    fn new(a: u64, n: u64, b: u64, s: u64, sqrt_floor: u64) -> Term {
        Term { front: a, numerator: n, bottom: b, s: s, sqrt_floor: sqrt_floor }
    }
}

fn iter_term(term: Term) -> Term {
    let n_next = (term.s - term.bottom.pow(2)) / term.numerator;
    let a_next = (term.sqrt_floor + term.bottom) / n_next;
    let b_next = (a_next * n_next) - term.bottom;
    Term::new(a_next, n_next, b_next, term.s, term.sqrt_floor)

    // let mut a_next = 0;
    // let mut n_next = term.numerator + 1;
    // if n_next > 10 {
    //     a_next = 1;
    //     n_next = 0;
    // }
    //
    // Term::new(a_next, n_next, term.bottom, term.s, term.sqrt_floor)
}

/// Credit: https://rosettacode.org/wiki/Cycle_detection#Python
/// Brent's cycle detection algorithm
/// Returns (a,b) where _a_ is the cycle length, and _b_ is the cycle start index (0-indexed)
/// This says the sequence 4,1,3,1,8,1,3,1,8,[1,3,1,8] repeats [1,3]
fn cycle_detection(f: fn(Term) -> Term, x0: Term) -> (u64, u64) {
    // main phase: search successive powers of two
    let mut power = 1;
    let mut lam = 1;
    let mut tortoise = x0;
    let mut hare = f(x0);   // f(x0) is the element/node next to x0.

    while tortoise.front != hare.front {
        if power == lam {   // time to start a new power of two?
            tortoise = hare;
            power *= 2;
            lam = 0
        }
        hare = f(hare);
        lam += 1
    }

    // Find the position of the first repetition of length lam
    let mut mu = 0;
    tortoise = x0;
    hare = x0;
    for _ in 0..lam {
        // range(lam) produces a list with the values 0, 1, ... , lam-1
        hare = f(hare);
    }
    // The distance between the hare and tortoise is now lam.

    // Next, the hare and tortoise move at same speed until they agree
    while tortoise.front != hare.front {
        tortoise = f(tortoise);
        hare = f(hare);
        mu += 1;
    }
    (lam, mu)
}

pub struct P064 {}
impl crate::Problem for P064 {
    fn run(&self, verbose: bool) -> (i32, String, String) {
        let mut t = Term::new(4, 1, 4, 23, 4);
        println!("{:?}", cycle_detection(iter_term, t));

        for _ in 0..32 {
            println!("{:?}", t);
            t = iter_term(t);
        }

        (64, "Odd periods".to_string(), "ERROR".to_string())
        // ???
    }
}
