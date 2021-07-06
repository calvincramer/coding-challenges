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
        }
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
    if max < 2 {
        return primes;
    }
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
    if num.is_prime() {
        return vec![num];
    }
    if num < 2 {
        return vec![];
    }
    let mut prime_factors = vec![];
    for div in 2..=num.integer_sqrt() {
        if num % div == 0 {
            if div.is_prime() {
                prime_factors.push(div);
            }
            if (num / div).is_prime() && (num / div) != div {
                prime_factors.push(num / div);
            }
        }
    }
    prime_factors
}

/// Gets all prime factors
pub fn get_prime_factors(mut num: u64) -> Vec<u64> {
    if num.is_prime() {
        return vec![num];
    }
    if num < 2 {
        return vec![];
    }
    let mut prime_factors: Vec<u64> = Vec::new();
    const SMALL_PRIMES: [u64; 25] = [
        2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89,
        97,
    ];
    for small_p in &SMALL_PRIMES {
        while num > 1 && num % small_p == 0 {
            prime_factors.push(*small_p);
            num /= small_p;
        }
        if num == 1 {
            break;
        }
    }
    let mut div = 101;
    while num > 1 {
        if !div.is_prime() {
            div += 2;
            continue;
        }
        while num > 1 && num % div == 0 {
            prime_factors.push(div);
            num /= div;
        }
        div += 2;
    }

    prime_factors
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

#[test]
fn test_get_prime_factors() {
    assert_eq!(get_prime_factors(0), vec![]);
    assert_eq!(get_prime_factors(1), vec![]);
    assert_eq!(get_prime_factors(2), vec![2]);
    assert_eq!(get_prime_factors(3), vec![3]);
    assert_eq!(get_prime_factors(4), vec![2, 2]);
    assert_eq!(get_prime_factors(5), vec![5]);
    assert_eq!(get_prime_factors(6), vec![2, 3]);
    assert_eq!(get_prime_factors(7), vec![7]);
    assert_eq!(get_prime_factors(8), vec![2, 2, 2]);
    assert_eq!(get_prime_factors(9), vec![3, 3]);
    assert_eq!(get_prime_factors(10), vec![2, 5]);
    assert_eq!(get_prime_factors(90), vec![2, 3, 3, 5]);
    assert_eq!(get_prime_factors(174), vec![2, 3, 29]);
    assert_eq!(get_prime_factors(390), vec![2, 3, 5, 13]);
    assert_eq!(get_prime_factors(394), vec![2, 197]);
    assert_eq!(get_prime_factors(596), vec![2, 2, 149]);
    assert_eq!(get_prime_factors(654), vec![2, 3, 109]);
    assert_eq!(get_prime_factors(792), vec![2, 2, 2, 3, 3, 11]);
    assert_eq!(get_prime_factors(870), vec![2, 3, 5, 29]);
    assert_eq!(get_prime_factors(975), vec![3, 5, 5, 13]);
    assert_eq!(get_prime_factors(46_758), vec![2, 3, 7_793]);
}
