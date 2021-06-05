extern crate integer_sqrt;
use integer_sqrt::IntegerSquareRoot;

/// Is prime?
/// Credit: http://stackoverflow.com/questions/1801391/what-is-the-best-algorithm-for-checking-if-a-number-is-prime
fn _impl_is_prime_faster(num: u64) -> bool {
    match num {
        _ if num < 2 => false,
        2 => true,
        3 => true,
        _ if num % 2 == 0 => false,
        _ if num % 3 == 0 => false,
        _ => {
            let mut i = 5;
            let mut w = 2;
            while i * i <= num {
                if num % i == 0 {
                    return false;
                }
                i += w;
                w = 6 - w;
            }
            true
        },
    }
}

/// Trait for types that can be tested for primality
pub trait PrimeTest {
    fn is_prime(&self) -> bool;
}

impl PrimeTest for u64 {
    fn is_prime(&self) -> bool {
        _impl_is_prime_faster(*self)
    }
}

impl PrimeTest for i64 {
    fn is_prime(&self) -> bool {
        match self {
            _ if *self < 0i64 => false,
            _ => _impl_is_prime_faster(*self as u64),
        }
    }
}

impl PrimeTest for i32 {
    fn is_prime(&self) -> bool {
        match self {
            _ if *self < 0i32 => false,
            _ => _impl_is_prime_faster(*self as u64),
        }
    }
}

pub fn get_primes_under(max: u64) -> Vec<u64> {
    let mut primes = vec![];
    if max < 2 { return primes; }
    primes.push(2);
    for n in (3..=max).step_by(2) {
        if n.is_prime() {
            primes.push(n)
        }
    }
    primes
}

/// Gets unique prime factors (slow)
pub fn get_uniq_prime_factors(num: u64) -> Vec<u64> {
    if num.is_prime() { return vec![num]; }
    if num < 2 { return vec![]; }
    let mut pfs = vec![];
    let sqrt = num.integer_sqrt();
    for div in 2..=sqrt {
        if num % div == 0 {
            if div.is_prime() {
                pfs.push(div);
            }
            if (num / div).is_prime() && (num / div) != div {
                pfs.push(num / div);
            }
        }
    }
    pfs
}

/// Is prime?
/// Very slow. Ok to use to check validity of other primality test implementations.
pub fn is_prime_sqrt_simple(num: u64) -> bool {
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

#[test]
fn test_is_prime() {
    // Are primes:
    assert!(2.is_prime());
    assert!(3.is_prime());
    assert!(5.is_prime());
    assert!(7.is_prime());
    assert!(11.is_prime());
    assert!(13.is_prime());
    assert!(17.is_prime());
    assert!(19.is_prime());

    // Are not primes:
    assert!(!(-19).is_prime());
    assert!(!(-1).is_prime());
    assert!(!0.is_prime());
    assert!(!1.is_prime());
    assert!(!4.is_prime());
    assert!(!6.is_prime());
    assert!(!8.is_prime());
    assert!(!9.is_prime());
    assert!(!10.is_prime());
    assert!(!12.is_prime());
    assert!(!14.is_prime());
    assert!(!15.is_prime());
}

#[test]
fn test_get_uniq_prime_factors() {
    assert_eq!(get_uniq_prime_factors(0), vec![]);
    assert_eq!(get_uniq_prime_factors(1), vec![]);
    assert_eq!(get_uniq_prime_factors(2), vec![2]);
    assert_eq!(get_uniq_prime_factors(3), vec![3]);
    assert_eq!(get_uniq_prime_factors(4), vec![2]);
    assert_eq!(get_uniq_prime_factors(5), vec![5]);
    assert_eq!(get_uniq_prime_factors(6), vec![2, 3]);
    assert_eq!(get_uniq_prime_factors(8), vec![2]);
    assert_eq!(get_uniq_prime_factors(9), vec![3]);
    assert_eq!(get_uniq_prime_factors(14), vec![2, 7]);
    assert_eq!(get_uniq_prime_factors(15), vec![3, 5]);
    assert_eq!(get_uniq_prime_factors(25), vec![5]);
    assert_eq!(get_uniq_prime_factors(644), vec![2, 7, 23]);
    assert_eq!(get_uniq_prime_factors(645), vec![3, 5, 43]);
    assert_eq!(get_uniq_prime_factors(646), vec![2, 17, 19]);

    assert_eq!(get_uniq_prime_factors(134043), vec![3, 7, 13, 491]);
    assert_eq!(get_uniq_prime_factors(134044), vec![2, 23, 31, 47]);
    assert_eq!(get_uniq_prime_factors(134045), vec![5, 17, 19, 83]);
    assert_eq!(get_uniq_prime_factors(134046), vec![2, 3, 11, 677]);
}
