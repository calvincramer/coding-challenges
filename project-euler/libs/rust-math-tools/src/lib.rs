extern crate integer_sqrt;
use integer_sqrt::IntegerSquareRoot;

pub fn is_prime(num: u64) -> bool {
    if num < 2 { return false }
    if num == 2 { return true }
    if num % 2 == 0 { return false }
    for div in (3..=num.integer_sqrt()+1).step_by(2) {
        if num % div == 0 { return false }
    }
    true
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn simple_primes() {
        assert!(is_prime(2));
        assert!(is_prime(3));
        assert!(is_prime(5));
        assert!(is_prime(7));
        assert!(is_prime(11));
        assert!(is_prime(13));
        assert!(is_prime(17));
        assert!(is_prime(19));

        assert!( !is_prime(0) );
        assert!( !is_prime(1) );
        assert!( !is_prime(4) );
        assert!( !is_prime(6) );
        assert!( !is_prime(8) );
        assert!( !is_prime(9) );
        assert!( !is_prime(10) );
        assert!( !is_prime(12) );
        assert!( !is_prime(14) );
        assert!( !is_prime(15) );
    }
}
