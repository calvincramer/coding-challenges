use num_integer::Roots;

/// Todo: cache sqrt

fn find_min_x(d: u64) -> u64 {
    // Loop over y
    let mut y = 1;
    loop {
        let rhs = d*y*y + 1;
        let rhs_sqrt = rhs.sqrt();
        if rhs_sqrt * rhs_sqrt == rhs {
            return rhs_sqrt;
        }
        y += 1;
    }
}

pub struct P066 {}
impl crate::Problem for P066 {
    #[allow(unused_variables)]
    fn run(&self, verbose: bool) -> (i32, String, String) {
        for d in 2u64..1_000 {
            if d.sqrt().pow(2) == d {
                continue;
            }
            println!("{} -> {}", d, find_min_x(d));
        }
        (66, "Max min x".to_string(), "???".to_string())
        // Answer: ???
    }
}
