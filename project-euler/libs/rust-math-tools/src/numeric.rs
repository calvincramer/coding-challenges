use crate::gcf::gcf;
/// Numeric functionality
use crate::palindrome::Palindrome;
use crate::prime::get_uniq_prime_factors;
use crate::reversible::reverse;

extern crate integer_sqrt;
use integer_sqrt::IntegerSquareRoot;

extern crate num_bigint;
use num_bigint::BigUint;
use std::ops::AddAssign;

extern crate num_traits;
use num_traits::One;
use num_traits::Zero;

use crate::PrimeTest;
use itertools::Itertools;

use std::collections::HashSet;
use std::iter::FromIterator;

/// Collatz sequence: if n is even divide number by 2, else if odd multiply by 3 and add 1, repeat until reach 1.
pub fn collatz_steps(mut num: u64) -> u64 {
    let mut steps = 1;
    while num != 1 {
        steps += 1;
        num = match num {
            n if num % 2 == 0 => n / 2,
            _ => (num * 3) + 1,
        };
    }
    steps
}

/// Get all divisors of a number (divisors from 1 to num inclusive)
pub fn divisors(num: u64) -> Vec<u64> {
    if num == 0 {
        return vec![];
    }
    let sqrt = num.integer_sqrt();
    let first_half: Vec<u64> = (1..=sqrt).filter(|div| num % div == 0).collect();
    let end = if sqrt.pow(2) == num {
        first_half.len() - 1
    } else {
        first_half.len()
    };
    let second_half: Vec<u64> = (0..end).rev().map(|i| num / first_half[i]).collect();
    [first_half, second_half].concat()
}

/// Get all proper divisors of a number (divisors strictly less than num)
pub fn divisors_proper(num: u64) -> Vec<u64> {
    let mut divs = divisors(num);
    if divs.len() > 0 && divs[divs.len() - 1] == num {
        divs.pop(); // Remove num from list of divisors
    }
    divs
}

/// Type comparing sum of proper divisors to number
#[derive(Debug, PartialEq, Eq)]
pub enum DivSum {
    Deficient = -1, // Sum of proper divisors is less than the number
    Perfect = 0,    // "" equal to the number
    Abundant = 1,   // "" greater than the number
}

/// Compare the sum of proper divisors of num to the num
pub fn divisors_sum_type(num: u64) -> DivSum {
    match divisors_proper(num).iter().sum::<u64>() {
        s if s < num => DivSum::Deficient,
        s if s > num => DivSum::Abundant,
        _ => DivSum::Perfect,
    }
}

/// Factorial but faster
/// Credit: http://www.luschny.de/math/factorial/FastFactorialFunctions.htm
/// This function seems to work by keeping most of the multiplication in smaller chunks (at the
/// leaf of recursion) and then multiply all chunks together. Multiplying smaller bigints will be
/// faster bigger bigints.
pub fn factorial(num: u64) -> BigUint {
    recurse_fact(1, num)
}
fn recurse_fact(start: u64, n: u64) -> BigUint {
    if n <= 16 {
        let mut r = BigUint::from(start);
        for i in (start + 1)..(start + n) {
            r = r * i;
        }
        return r;
    }
    let h = n / 2; // half
    recurse_fact(start, h) * recurse_fact(start + h, n - h)
}
/// TODO: Faster factorial: http://www.luschny.de/math/factorial/csharp/FactorialSplit.cs.html

/// Factorial - simple implementation
pub fn factorial_slow(num: u64) -> BigUint {
    let mut result = BigUint::one();
    for mult in 2..=num {
        result = result * mult;
    }
    result
}

/// Lychrel numbers are numbers that never produce palindromes by reversing and adding itself
pub fn is_lychrel(num: u64) -> bool {
    const MAX_TESTS: i32 = 50; // Stop after this number of iterations
    let mut n = BigUint::from(num);
    for _ in 0..=MAX_TESTS {
        n.add_assign(reverse(&n));
        if n.to_string().is_palindrome() {
            return false;
        }
    }
    true
}

/// Is a number a square of an integer?
pub fn is_square(num: &u64) -> bool {
    *num == (*num).integer_sqrt().pow(2)
}

/// nCr - number of combinations
pub fn ncr(n: u64, r: u64) -> BigUint {
    if r > n {
        return BigUint::zero();
    }
    let n_fact = factorial(n);
    let r_fact = factorial(r);
    let n_minus_r_fact = factorial(n - r);
    n_fact / (r_fact * n_minus_r_fact)
}

/// Gets the number of digits in a number
pub fn num_digits(mut num: u64) -> u64 {
    if num == 0 {
        return 1;
    }
    let mut count = 0;
    while num > 0 {
        num /= 10;
        count += 1;
    }
    count
}

/// Gets the number of digits in a number
pub fn num_digits_128(mut num: u128) -> u128 {
    if num == 0 {
        return 1;
    }
    let mut count = 0;
    while num > 0 {
        num /= 10;
        count += 1;
    }
    count
}

/// Get the number of divisors (divisors from 1 to num inclusive)
pub fn num_divisors(num: u64) -> u64 {
    if num == 0 {
        return 0;
    }
    let sqrt = num.integer_sqrt();
    let mut total = 0;
    for div in 1..=sqrt {
        if num % div == 0 {
            total += 2;
        }
    }
    if sqrt.pow(2) == num {
        total -= 1; // Square numbers on count sqrt once (ex: 25)
    }
    total
}

/// Slow
pub fn get_factors(num: u64) -> Vec<u64> {
    let mut factors = vec![];
    let sqrt = num.integer_sqrt();
    for div in 2..sqrt {
        if num % div == 0 {
            factors.push(div);
            factors.push(num / div);
        }
    }
    if sqrt.pow(2) == num {
        factors.push(sqrt);
    } else if num % sqrt == 0 {
        factors.push(sqrt);
        factors.push(num / sqrt);

    }
    factors.sort();
    factors
}

/// Gets all factorizations of a number, returned in no particular order
/// 12 -> 12, 2*6, 2*2*3, 4*3
pub fn get_all_factorizations(num: u64) -> Vec<Vec<u64>> {
    if num.is_prime() {
        return vec![vec![num]];
    }
    let mut result = vec![vec![num]];
    for div in get_factors(num) {
        let rest = num / div;
        let mut rest_results = get_all_factorizations(rest);
        for i in 0..rest_results.len() {
            let pos_to_insert = match rest_results[i].binary_search(&div) {
                Ok(pos) => pos,
                Err(pos) => pos,
            };
            rest_results[i].insert(pos_to_insert, div);
        }
        result.append(&mut rest_results);
    }
    Vec::from_iter(HashSet::<Vec<u64>>::from_iter(result))

}

/// Returns all pandigitals of digits made of of 'from' to 'to'
/// Example: get_pandigitals(2, 4) -> [2,3,4], [2,4,3], [3,2,4], [3,4,2], [4,2,3], [4,3,2]
pub fn get_pandigitals(from: u8, to: u8) -> Vec<u64> {
    if from > 9 || to > 9 {
        panic!("from and to cannot be greater than 9");
    }
    (from..=to)
        .permutations((to - from + 1) as usize)
        .map(|v| {
            v.iter()
                .map(|n| n.to_string())
                .join("")
                .parse::<u64>()
                .unwrap()
        })
        .collect()
}

/// num has all digits from from_digit to to_digit inclusive? (faster than implementation with HashSet)
pub fn is_pandigital(mut num: u64, from_digit: u8, to_digit: u8) -> bool {
    let mut found = [false; 10];
    let mut num_found = 0;
    while num > 0 {
        let d = (num % 10) as usize;
        if d < from_digit as usize || d > to_digit as usize || found[d] == true {
            return false;
        }
        found[d] = true;
        num_found += 1;
        num /= 10;
    }
    return num_found == to_digit - from_digit + 1;
}
/// Specialized version even faster than above
pub fn is_pandigital_1_to_9(mut num: u64) -> bool {
    if num < 123_456_789 || num > 987_654_321 {
        return false;
    }
    let mut found = [false; 10];
    while num > 0 {
        let d = (num % 10) as usize;
        if d == 0 || found[d] == true {
            return false;
        }
        found[d] = true;
        num /= 10;
    }
    true
}

/// Euler's totient function. AKA "phi" function
/// Counts the number of numbers less than n which are relatively prime to n
pub fn totient(n: u64) -> u64 {
    let factors = get_uniq_prime_factors(n);
    if factors.len() == 0 {
        return n;
    }
    let mut top = 1;
    let mut bottom = 1;
    for n in factors {
        top *= n - 1;
        bottom *= n;
    }
    n * top / bottom
}
pub fn totient_slow(n: u64) -> u64 {
    (1..n).filter(|x| gcf(x, &n) == 1).count() as u64
}

pub trait SumDigits {
    fn sum_digits(&self) -> u64;
}

impl SumDigits for BigUint {
    fn sum_digits(&self) -> u64 {
        self.to_string().chars().map(|c| (c as u64) - 0x30).sum()
    }
}

/// Permutes an array of numbers from 0 to N once. Returns false if no more permutations (numbers are descending)
/// WARNING: this function should only be used with numbers increasing from 0, like [0, 1, 2, 3]. No other numbers are tested.
/// WARNING: this function may be inefficient.
pub fn permute_once(arr: &mut [u64]) -> bool {
    // Find first ascending index:
    let mut fd = arr.len(); // First decrease(?)
    for i in (1..arr.len()).rev() {
        if arr[i] > arr[i - 1] {
            fd = i - 1;
            break;
        }
    }
    // All numbers descending === no more permutations
    if fd == arr.len() {
        return false;
    }

    let mut inc_uniq = false;
    while !inc_uniq {
        arr[fd] += 1;
        inc_uniq = true; // Assume we incremented to a unique number (to the left) first
        for i in 0..fd {
            if arr[i] == arr[fd] {
                inc_uniq = false;
            }
        }
    }

    // Fill in numbers to the right of fd
    for i in fd + 1..arr.len() {
        // For each index from right of decrease to end
        for num_try in 0..arr.len() {
            let mut found_good = true;
            for index in 0..i {
                if arr[index] == num_try as u64 {
                    found_good = false;
                    break;
                }
            }
            if found_good {
                arr[i] = num_try as u64;
                break;
            }
        }
    }
    true
}

#[cfg(test)]
mod lib_rs_tests {
    use super::*;

    #[test]
    fn test_is_square() {
        assert!(is_square(&0));
        assert!(is_square(&1));
        assert!(is_square(&4));
        assert!(is_square(&9));
        assert!(is_square(&16));
        assert!(is_square(&25));
        assert!(is_square(&36));
        assert!(is_square(&49));

        assert!(!is_square(&2));
        assert!(!is_square(&3));
        assert!(!is_square(&5));
        assert!(!is_square(&6));
        assert!(!is_square(&101));
    }

    #[test]
    fn test_divisors() {
        assert_eq!(divisors(0), vec![]);
        assert_eq!(divisors(1), vec![1]);
        assert_eq!(divisors(2), vec![1, 2]);
        assert_eq!(divisors(3), vec![1, 3]);
        assert_eq!(divisors(4), vec![1, 2, 4]);
        assert_eq!(divisors(6), vec![1, 2, 3, 6]);
        assert_eq!(divisors(10), vec![1, 2, 5, 10]);
        assert_eq!(divisors(15), vec![1, 3, 5, 15]);
        assert_eq!(divisors(16), vec![1, 2, 4, 8, 16]);
        assert_eq!(divisors(28), vec![1, 2, 4, 7, 14, 28]);
    }

    #[test]
    fn test_num_divisors() {
        assert_eq!(num_divisors(0), 0);
        assert_eq!(num_divisors(1), 1);
        assert_eq!(num_divisors(2), 2);
        assert_eq!(num_divisors(3), 2);
        assert_eq!(num_divisors(4), 3);
        assert_eq!(num_divisors(6), 4);
        assert_eq!(num_divisors(10), 4);
        assert_eq!(num_divisors(15), 4);
        assert_eq!(num_divisors(16), 5);
        assert_eq!(num_divisors(28), 6);
    }

    #[test]
    fn test_collatz_steps() {
        assert_eq!(collatz_steps(1), 1);
        assert_eq!(collatz_steps(13), 10);
        assert_eq!(collatz_steps(837799), 525);
    }

    fn test_factorial_common(f: fn(u64) -> BigUint) {
        assert_eq!(f(0), BigUint::one());
        assert_eq!(f(1), BigUint::one());
        assert_eq!(f(2), BigUint::parse_bytes(b"2", 10).unwrap());
        assert_eq!(f(6), BigUint::parse_bytes(b"720", 10).unwrap());
        assert_eq!(
            f(20),
            BigUint::parse_bytes(b"2432902008176640000", 10).unwrap()
        );
        assert_eq!(
            f(20),
            BigUint::parse_bytes(b"2432902008176640000", 10).unwrap()
        );
        assert_eq!(f(100), BigUint::parse_bytes(b"93326215443944152681699238856266700490715968264381621468592963895217599993229915608941463976156518286253697920827223758251185210916864000000000000000000000000", 10).unwrap());
    }

    #[test]
    fn test_factorial() {
        test_factorial_common(factorial);
    }

    #[test]
    fn test_factorial_slow() {
        test_factorial_common(factorial_slow);
    }

    #[test]
    fn test_divisors_sum_type() {
        use DivSum::{Abundant, Deficient, Perfect};
        assert_eq!(divisors_sum_type(12), Abundant);
        assert_eq!(divisors_sum_type(28), Perfect);
        assert_eq!(divisors_sum_type(31), Deficient);
    }

    #[test]
    fn test_ncr() {
        assert_eq!(ncr(12, 6), BigUint::from(924u64));
        assert_eq!(ncr(15, 2), BigUint::from(105u64));
        assert_eq!(ncr(34, 14), BigUint::from(1391975640u64));
    }

    #[test]
    fn test_num_digits() {
        assert_eq!(num_digits(0), 1);
        assert_eq!(num_digits(4), 1);
        assert_eq!(num_digits(46), 2);
        assert_eq!(num_digits(423), 3);
        assert_eq!(num_digits(4267), 4);
        assert_eq!(num_digits(45247), 5);
        assert_eq!(num_digits(465731), 6);
        assert_eq!(num_digits(4888886), 7);
        assert_eq!(num_digits(42345224), 8);
    }

    #[test]
    fn test_is_lychrel() {
        assert_eq!(is_lychrel(47), false);
        assert_eq!(is_lychrel(1234321), false);
        assert_eq!(is_lychrel(349), false);
        assert_eq!(is_lychrel(196), true);
        assert_eq!(is_lychrel(4994), true); // palindromes are not necessarily lychrel
    }

    #[test]
    fn test_totient() {
        assert_eq!(totient(2), 1);
        assert_eq!(totient(3), 2);
        assert_eq!(totient(4), 2);
        assert_eq!(totient(5), 4);
        assert_eq!(totient(6), 2);
        assert_eq!(totient(7), 6);
        assert_eq!(totient(8), 4);
        assert_eq!(totient(9), 6);
        assert_eq!(totient(10), 4);
    }

    #[test]
    fn test_is_pandigital() {
        assert_eq!(is_pandigital(123456789, 1, 9), true);
        assert_eq!(is_pandigital(987654321, 1, 9), true);
        assert_eq!(is_pandigital(87654321, 1, 9), false);
        assert_eq!(is_pandigital(1, 1, 9), false);
        assert_eq!(is_pandigital(111111111, 1, 9), false);
        assert_eq!(is_pandigital(102345678, 1, 9), false);
    }

    #[test]
    fn test_is_pandigital_1_to_9() {
        assert_eq!(is_pandigital_1_to_9(123456789), true);
        assert_eq!(is_pandigital_1_to_9(987654321), true);
        assert_eq!(is_pandigital_1_to_9(87654321), false);
        assert_eq!(is_pandigital_1_to_9(1), false);
        assert_eq!(is_pandigital_1_to_9(111111111), false);
        assert_eq!(is_pandigital_1_to_9(102345678), false);
    }

    #[test]
    fn test_get_pandigitals() {
        assert_eq!(get_pandigitals(5, 7), vec![567, 576, 657, 675, 756, 765]);
    }

    #[test]
    /// TODO
    fn test_get_all_factorizations() {
        assert_eq!(
            get_all_factorizations(12),
            vec![vec![12], vec![2, 6], vec![2, 2, 3]]
        );
        assert_eq!(
            get_all_factorizations(24),
            vec![vec![24], vec![2, 12], vec![2, 2, 6], vec![2, 2, 2, 3]]
        );
        assert_eq!(
            get_all_factorizations(100),
            vec![vec![100], vec![2, 50], vec![2, 2, 25], vec![2, 2, 5, 5]]
        );
        assert_eq!(
            get_all_factorizations(963),
            vec![vec![963], vec![3, 321], vec![3, 3, 107]]
        );
        // 16: 2*8, 2*2*4, 2*2*2*2, 4*4
    }

    #[test]
    fn test_get_factors() {
        assert_eq!(get_factors(10), vec![2, 5]);
        assert_eq!(get_factors(16), vec![2, 4, 8]);
    }
}
