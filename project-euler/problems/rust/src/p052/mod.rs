fn same_digits(mut num_other: u64, mut digits: [u8; 10]) -> bool {
    while num_other > 0 {
        let d = (num_other % 10) as usize;
        match digits[d] {
            0 => return false,
            _ => digits[d] -= 1,
        }
        num_other /= 10;
    }
    true
}

fn build_digits(mut num: u64) -> [u8; 10] {
    let mut digits = [0u8; 10];
    while num > 0 {
        digits[(num % 10) as usize] += 1;
        num /= 10;
    }
    digits
}

pub struct P052 {}
impl crate::Problem for P052 {
    #[allow(unused_variables)]
    fn run(&self, verbose: bool) -> (i32, String, String) {
        const MAX_MULT: u64 = 6;
        let mut n = 10;
        loop {
            let _1x_digits = build_digits(n);
            let mut good = true;
            for mult in 2..=MAX_MULT {
                if ! same_digits(mult * n, _1x_digits) {
                    good = false;
                    break;
                }
            }
            if good {
                return (52, "Smallest".to_string(), n.to_string())
            }
            n += 1;
        }
        // Answer: 142857
    }
}
