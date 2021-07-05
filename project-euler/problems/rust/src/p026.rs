use bigdecimal::BigDecimal;
use num_traits::One;

/// longest reocurring cycle:
///     default n_digits = 10
///
///     do:
///         make n_digits larger
///         bigdecimal = 1 / n with n_digits
///     while ! found cycle()
///     return length of cycle
///
/// find cycle(string):
///     start from right side of string
///     for cycle_length in 1..(len(string) // 2):
///         is repeating cycle_length on right side of string?
///             yes -> return something
///     return fail

fn find_largest_cycle(n: u64) -> Option<String> {
    let mut prec: u64 = n * 3;     // Cycle may be as long as denominator (theorem?)
    let mut last_len: usize = 0;   // Last size after cutting off ending zeroes (for non-repeating numbers)
    loop {
        let frac: BigDecimal = BigDecimal::one();
        let frac = frac.with_prec(prec);
        let frac = &frac / n;
        let frac_str = frac.to_string();
        let frac_str = frac_str.as_bytes();

        let mut cut_i = frac_str.len() - 1;     // CUT OFF THE LAST DIGIT: IT MAY BE ROUNDED.
        while cut_i > 0 && frac_str[cut_i - 1] == ('0' as u8) {
            cut_i -= 1;
        }

        if cut_i == last_len {
            return None;
        } else {
            last_len = cut_i;
        }

        match find_cycle(&frac_str[0..cut_i]) {
            Some(str) => return Some(str),
            None => prec *= 2,
        }

        if prec > 100_000 {
            panic!("Can't find cycle {}", n);
        }
    }
}

fn find_cycle(s: &[u8]) -> Option<String> {
    let chars = &s[2..];

    for cycle_len in 1..(chars.len() / 3) {
        if is_repeating_right(cycle_len, chars) {
            return Some(std::str::from_utf8(&chars[chars.len() - cycle_len..]).unwrap().to_string())
        }
    }
    None
}

fn is_repeating_right(cycle_length: usize, chars: &[u8]) -> bool {
    // Match at cycle_length at least three times to be considered a cycle:
    if cycle_length * 3 > chars.len() {
        return false;
    }
    // Indexes:
    let i1 = chars.len();
    let i2 = i1 - cycle_length;
    let i3 = i2 - cycle_length;
    let i4 = i3 - cycle_length;
    // Substring equality:
    return (chars[i2..i1] == chars[i3..i2]) && (chars[i2..i1] == chars[i4..i3])
}

pub struct P026 {}
impl crate::Problem for P026 {
    fn solve(&self, verbose: bool) -> String {
        let mut max_d = 0;
        let mut max_cycle = "".to_string();
        for d in 983..1000 {
            let temp_cycle = find_largest_cycle(d);
            match temp_cycle {
                Some(cycle_str) => {
                    if verbose {
                        println!("d={} len={} cycle={}", d, cycle_str.len(), cycle_str);
                    }
                    if cycle_str.len() > max_cycle.len() {
                        max_cycle = cycle_str;
                        max_d = d;
                    }
                },
                None => {
                    if verbose {
                        println!("d={} no cycle", d);
                    }
                },
            }
        }
        if verbose {
            println!("\nd={} len={} cycle={}", max_d, max_cycle.len(), max_cycle)
        }
        max_d.to_string()
    }
    fn is_slow(&self) -> bool { false }
    fn problem_num(&self) -> i32 { 26 }
    fn answer_desc(&self) -> String { "d".to_string() }
    fn real_answer(&self) -> crate::ProblemAnswer { crate::ProblemAnswer::Some("983".to_string()) }
}
