pub struct P009 {}
impl crate::Problem for P009 {
    fn run(&self, verbose: bool) -> (i32, String, String) {
        for a in 1i64..1000 {
            for b in a + 1..1000 {
                let c: i64 = 1000 - a - b;   // Only one option for c
                if a.pow(2) + b.pow(2) == c.pow(2) {
                    if verbose {
                        println!("a={} b={} c={}", a, b, c);
                        println!("a+b+c={}", a + b + c);
                    }
                    return (9, "a*b*c".to_string(), (a * b * c).to_string());
                    // Supposedly there is one one of this triplet, but continue on just in case
                }
            }
        }
        (9, "a*b*c".to_string(), "ERROR".to_string())
        // Answer: 31875000
    }
}
