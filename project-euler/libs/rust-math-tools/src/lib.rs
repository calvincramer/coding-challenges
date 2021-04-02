extern crate integer_sqrt;
use integer_sqrt::IntegerSquareRoot;

pub fn is_prime(num: u64) -> bool {
    if num < 2 {
        return false;
    }
    if num == 2 {
        return true;
    }
    if num % 2 == 0 {
        return false;
    }
    for div in (3..=num.integer_sqrt() + 1).step_by(2) {
        if num % div == 0 {
            return false;
        }
    }
    true
}

pub fn is_palindrome(num: u64) -> bool {
    let s: String = num.to_string();
    let _len = s.len();
    let bytes = s.as_bytes();

    if _len <= 1 {
        return true;
    }

    let mut left: usize = 0;
    let mut right: usize = _len - 1;

    while bytes[left] == bytes[right] {
        left += 1;
        right -= 1;
        if right <= left {
            return true;
        }
    }
    false
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

        assert!(!is_prime(0));
        assert!(!is_prime(1));
        assert!(!is_prime(4));
        assert!(!is_prime(6));
        assert!(!is_prime(8));
        assert!(!is_prime(9));
        assert!(!is_prime(10));
        assert!(!is_prime(12));
        assert!(!is_prime(14));
        assert!(!is_prime(15));
    }

    #[test]
    fn palindromes() {
        assert!(is_palindrome(1));
        assert!(is_palindrome(11));
        assert!(is_palindrome(121));
        assert!(is_palindrome(12321));
        assert!(is_palindrome(100001));
        assert!(is_palindrome(1000001));
        assert!(is_palindrome(10000001));

        assert!(!is_palindrome(10));
        assert!(!is_palindrome(123));
        assert!(!is_palindrome(1234));
    }
}
