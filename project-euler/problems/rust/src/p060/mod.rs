use rust_math_tools::get_primes_under;
use rust_math_tools::PrimeTest;

fn concat_numbers(a: u64, b: u64) -> u64 {
    let mut s = a.to_string();
    s.push_str(&b.to_string());
    s.parse::<u64>().unwrap()
}

fn test(a: u64, b: u64) -> bool {
    concat_numbers(a, b).is_prime() && concat_numbers(b, a).is_prime()
}

pub struct P060 {}
impl crate::Problem for P060 {
    #[rustfmt::skip]
    fn run(&self, verbose: bool) -> (i32, String, String) {
        let primes = get_primes_under(10_000);

        for i0 in 0..primes.len() {
        for i1 in (i0+1)..primes.len() {
            if !test(primes[i0], primes[i1]) { continue; }
        for i2 in (i1+1)..primes.len() {
            if !test(primes[i0], primes[i2]) ||
                !test(primes[i1], primes[i2]) { continue; }
        for i3 in (i2+1)..primes.len() {
            if !test(primes[i0], primes[i3]) ||
                !test(primes[i1], primes[i3]) ||
                !test(primes[i2], primes[i3]) { continue; }
        for i4 in (i3+1)..primes.len() {
            if !test(primes[i0], primes[i4]) ||
                !test(primes[i1], primes[i4]) ||
                !test(primes[i2], primes[i4]) ||
                !test(primes[i3], primes[i4]) { continue; }
            // Pass
            if verbose {
                println!("{} {} {} {} {}", primes[i0], primes[i1], primes[i2], primes[i3], primes[i4]);
            }
            let sum = primes[i0] + primes[i1] + primes[i2] + primes[i3] + primes[i4];
            return (60, "Sum 5 primes".to_string(), sum.to_string())
        }}}}}

        (60, "Sum 5 primes".to_string(), "ERROR".to_string())
        // Answer: 26033
    }
}
