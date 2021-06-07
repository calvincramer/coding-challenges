pub struct P039 {}

#[rustfmt::skip]
#[allow(dead_code)]
fn try1(verbose: bool) -> i64 {
    let mut max_num_triples = 0;
    let mut max_p = 0;
    for p in 1i64..=1000 {
        let mut num_triples = 0;
        for a in 1..p {
            for b in 1..p {
                let c = p - a - b;
                if a*a + b*b == c*c { num_triples += 1; }
            }}
        if num_triples > max_num_triples {
            max_num_triples = num_triples;
            max_p = p;
        }
    }
    if verbose {
        println!("{} {}", max_p, max_num_triples)
    }
    max_p
}

#[rustfmt::skip]
fn try2(verbose: bool) -> i64 {
    let mut max_num_triples = 0;
    let mut max_p = 0;
    for p in 1i64..=1000 {
        let mut num_triples = 0;
        for a in 1..p {
            // Let a by the 'x' side of our triangle, and b to be 'y' side
            let top = p * (p - 2 * a);
            let bottom = 2 * (p - a);
            if top % bottom != 0 {
                continue;   // y side is not an integer
            }
            // let b = top / bottom;
            // Have integer a, b such that a + b + c == p
            // Since p, a, and b are integers, c is an integer and we have a solution
            num_triples += 1;
        }
        if num_triples > max_num_triples {
            max_num_triples = num_triples;
            max_p = p;
        }
    }
    if verbose {
        println!("{} {}", max_p, max_num_triples)
    }
    max_p
}

impl crate::Problem for P039 {
    fn solve(&self, verbose: bool) -> String {
        try2(verbose).to_string()
    }
    fn is_slow(&self) -> bool { false }
    fn problem_num(&self) -> i32 { 39 }
    fn answer_desc(&self) -> String { "Max perimeter".to_string() }
    fn real_answer(&self) -> crate::ProblemAnswer { crate::ProblemAnswer::Some("840".to_string()) }
}
