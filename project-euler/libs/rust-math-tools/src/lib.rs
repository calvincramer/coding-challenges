extern crate integer_sqrt;
use integer_sqrt::IntegerSquareRoot;

extern crate num_bigint;
use num_bigint::BigUint;

extern crate num_traits;
use num_traits::One;

use std::fs::File;
use std::io::BufReader;
use std::io::BufRead;

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
    if num == 0 { return vec![]; }
    let sqrt = num.integer_sqrt();
    let first_half : Vec<u64> = (1..=sqrt).filter(|div| num % div == 0).collect();
    let end = if sqrt.pow(2) == num { first_half.len() - 1 } else { first_half.len() };
    let second_half : Vec<u64> = (0..end).rev().map(|i| num / first_half[i]).collect();
    [first_half, second_half].concat()
}

/// Get all proper divisors of a number (divisors strictly less than num)
pub fn divisors_proper(num: u64) -> Vec<u64> {
    let mut divs = divisors(num);
    if divs.len() > 0 && divs[divs.len() - 1] == num {
        divs.pop();  // Remove num from list of divisors
    }
    divs
}

/// Type comparing sum of proper divisors to number
#[derive(Debug)]
#[derive(PartialEq, Eq)]
pub enum DivSum {
    Deficient = -1,     // Sum of proper divisors is less than the number
    Perfect = 0,        // "" equal to the number
    Abundant = 1,       // "" greater than the number
}

/// Compare the sum of proper divisors of num to the num
pub fn divisors_sum_type(num: u64) -> DivSum {
    match divisors_proper(num).iter().sum::<u64>() {
        s if s < num => DivSum::Deficient,
        s if s > num => DivSum::Abundant,
        _ => DivSum::Perfect,
    }
}

/// Factorial
pub fn factorial(num: u64) -> BigUint {
    let mut result = BigUint::one();
    for mult in 2..=num {
        result = result * mult;
    }
    result
}

pub struct Fraction {
    pub numerator: i64,
    pub denominator: i64,
}
impl Fraction {
    pub fn val(&self) -> f64 {
        self.numerator as f64 / self.denominator as f64
    }

    pub fn reduce(&mut self) {
        let gcf = gcf(self.numerator as u64, self.denominator as u64) as i64;
        self.numerator /= gcf;
        self.denominator /= gcf;
    }
}
impl std::fmt::Display for Fraction {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        write!(f, "{} / {}", self.numerator, self.denominator)
    }
}

/// Returns the greatest common factor between two numbers
pub fn gcf(mut n1: u64, mut n2: u64) -> u64 {
    if n1 == 0 {
        return n2;
    }
    while n2 != 0 {
        let r = n1 % n2;
        n1 = n2;
        n2 = r;
    }
    return n1;
}

fn _is_palindrome_string(s: String) -> bool {
    let _len = s.len();
    if _len <= 1 {
        return true;
    }
    let bytes = s.as_bytes();
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

/// Is a palindrome? (like "12321")
pub trait Palindrome {
    fn is_palindrome(self) -> bool;
}
impl Palindrome for u64 {
    fn is_palindrome(self) -> bool {
        _is_palindrome_string(self.to_string())
    }
}
impl Palindrome for i32 {
    fn is_palindrome(self) -> bool {
        match self {
            _ if self < 0 => panic!("Have not implement palindrome for negative numbers!"),
            _ => _is_palindrome_string(self.to_string()),
        }
    }
}
impl Palindrome for String {
    fn is_palindrome(self) -> bool {
        _is_palindrome_string(self)
    }
}

/// num has all digits from from_digit to to_digit inclusive?
pub fn is_pandigital(mut num: u64, from_digit: u8, to_digit: u8) -> bool {
    // let already_found_num = 10;
    // if to_digit >= 10 {
    //     return false;
    // }
    // let mut digits = vec![];
    // for d in from_digit..=to_digit {
    //     digits.push(d);
    // }

    let mut found: HashSet<u8> = HashSet::new();
    while num > 0 {
        let d: u8 = (num % 10) as u8;
        match found.contains(&d) {
            true => return false,
            false => found.insert(d),
        };
        num /= 10;
    }
    return found == HashSet::from_iter(from_digit..=to_digit);
}

/// Is prime?
fn _is_prime_sqrt_simple(num: u64) -> bool {
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

/// Is prime?
/// Credit: http://stackoverflow.com/questions/1801391/what-is-the-best-algorithm-for-checking-if-a-number-is-prime
fn _is_prime_faster(num: u64) -> bool {
    match num {
        _ if num < 2 => false,
        2 => true,
        3 => true,
        _ if num % 2 == 0 => false,
        _ if num % 3 == 0 => false,
        _ => {
            let mut i = 5;
            let mut w = 2;
            while i*i <= num {
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
    fn is_prime(self) -> bool;
}

impl PrimeTest for u64 {
    fn is_prime(self) -> bool {
        return _is_prime_faster(self);
    }
}
impl PrimeTest for i64 {
    fn is_prime(self) -> bool {
        match self {
            _ if self < 0i64 => false,
            _ => _is_prime_faster(self as u64),
        }
    }
}
impl PrimeTest for i32 {
    fn is_prime(self) -> bool {
        match self {
            _ if self < 0i32 => false,
            _ => _is_prime_faster(self as u64),
        }
    }
}

/// Is a number a square of an integer?
pub fn is_square(num: u64) -> bool {
    num == num.integer_sqrt().pow(2)
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

/// Get the number of divisors (divisors from 1 to num inclusive)
pub fn num_divisors(num: u64) -> u64 {
    if num == 0 { return 0; }
    let sqrt = num.integer_sqrt();
    let mut total = 0;
    for div in 1..=sqrt {
        if num % div == 0 {
            total += 2;
        }
    }
    if sqrt.pow(2) == num {
        total -= 1;     // Square numbers on count sqrt once (ex: 25)
    }
    total
}

/// Number to english string representation
/// I know it looks ugly. Still getting used to strings in Rust.
pub fn num_to_string(num: i64) -> String {
    if num == 0 {
        return "zero".to_string();
    }
    let table = ["", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"];
    let table_tens = ["", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"];
    let table_special = ["ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"];

    let _thou_to_str = |n : u16| {
        let digit_1 = n % 10;
        let digit_10 = (n / 10) % 10;
        let digit_100 = (n / 100) % 10;
        let mut hundreds = format!("{}{}", table[(n / 100) as usize], if digit_100 > 0 { " hundred "} else { "" });
        if digit_100 > 0 && n % 100 > 0 {
            hundreds = format!("{}and ", hundreds);
        }

        let rest = match digit_10 {
            // If tens digit is 0, just do less than 10 numbers:
            0 => table[digit_1 as usize].to_string(),
            // Special English words for eleven, twelve...:
            1 => table_special[digit_1 as usize].to_string(),
            2..=9 => {
                if digit_1 != 0 {
                    table_tens[digit_10 as usize].to_string() + &"-".to_string() + &table[digit_1 as usize].to_string()
                } else {
                    table_tens[digit_10 as usize].to_string() + &table[digit_1 as usize].to_string()
                }
            },
            _ => panic!("bad"),
        };
        hundreds + &rest
    };

    let negative = if num < 0 { true } else { false };
    let mut num_s = num.abs().to_string();
    let mut endings_i = 0;
    let endings = [
        "", "thousand", "million", "billion", "trillion", "quadrillion", "quintillion",
        "sextillion", "septillion", "octillion", "nonillion", "decillion", "undecillion",
        "duodecillion", "tredecillion", "quattuordecillion", "quindecillion", "sexdecillion",
        "septendecillion", "octodecillion", "novemdecillion", "vigintillion", ];
    let mut s = String::new();
    loop {
        let temp_3digs = if num_s.len() >= 3 { num_s[num_s.len()-3..].parse::<u16>().unwrap() }
                              else { num_s.parse::<u16>().unwrap() };
        s = format!("{} {} {}", _thou_to_str(temp_3digs), endings[endings_i].to_string(), s);
        endings_i += 1;
        num_s = if num_s.len() >= 3 { num_s[..num_s.len()-3].to_string() } else { "".to_string() };
        if !(num_s.len() > 0 && endings_i < endings.len()) { break; }
    }
    if num_s.len() > 0 {
        s = format!("SOME BIGGER NUMBERS AND {}", s);
    }
    if negative {
        s = format!("negative {}", s);
    }
    s.trim_end().to_string()
}

/// Permutes an array of numbers from 0 to N once. Returns false if no more permutations (numbers are descending)
/// WARNING: this function should only be used with numbers increasing from 0, like [0, 1, 2, 3]. No other numbers are tested.
/// WARNING: this function may be inefficient.
pub fn permute_once(arr: &mut [u64]) -> bool {
    // Find first ascending index:
    let mut fd = arr.len();     // First decrease(?)
    for i in (1..arr.len()).rev() {
        if arr[i] > arr[i-1] {
            fd = i-1;
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
        inc_uniq = true;    // Assume we incremented to a unique number (to the left) first
        for i in 0..fd {
            if arr[i] == arr[fd] {
                inc_uniq = false;
            }
        }
    }

    // Fill in numbers to the right of fd
    for i in fd+1..arr.len() {  // For each index from right of decrease to end
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

/// Opens and reads a file specified by path. Panics if file doesn't exist
pub fn read_all_lines(path: String) -> Vec<String> {
    let f = File::open(path);
    match f {
        Err(e) => panic!("File doesn't exist: {}", e),
        Ok(file) =>  BufReader::new(file).lines().map(Result::unwrap).collect(),
    }
}

#[cfg(test)]
mod tests {
    use super::*;

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
    fn test_is_palindrome() {
        assert!(1.is_palindrome());
        assert!(11.is_palindrome());
        assert!(121.is_palindrome());
        assert!(12321.is_palindrome());
        assert!(100001.is_palindrome());
        assert!(1000001.is_palindrome());
        assert!(10000001.is_palindrome());

        assert!(!(10).is_palindrome());
        assert!(!(123).is_palindrome());
        assert!(!(1234).is_palindrome());

        assert!("abcdedcba".to_string().is_palindrome())
    }

    #[test]
    fn test_is_square() {
        assert!(is_square(0));
        assert!(is_square(1));
        assert!(is_square(4));
        assert!(is_square(9));
        assert!(is_square(16));
        assert!(is_square(25));
        assert!(is_square(36));
        assert!(is_square(49));

        assert!(!is_square(2));
        assert!(!is_square(3));
        assert!(!is_square(5));
        assert!(!is_square(6));
        assert!(!is_square(101));
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

    #[test]
    fn test_num_to_string() {
        assert_eq!(num_to_string(0), "zero");
        assert_eq!(num_to_string(5), "five");
        assert_eq!(num_to_string(14), "fourteen");
        assert_eq!(num_to_string(-14), "negative fourteen");
        assert_eq!(num_to_string(30), "thirty");
        assert_eq!(num_to_string(-511), "negative five hundred and eleven");
        assert_eq!(num_to_string(123456), "one hundred and twenty-three thousand four hundred and fifty-six");
    }

    #[test]
    fn test_factorial() {
        assert_eq!(factorial(0), BigUint::one());
        assert_eq!(factorial(1), BigUint::one());
        assert_eq!(factorial(2), BigUint::parse_bytes(b"2", 10).unwrap());
        assert_eq!(factorial(6), BigUint::parse_bytes(b"720", 10).unwrap());
        assert_eq!(factorial(20), BigUint::parse_bytes(b"2432902008176640000", 10).unwrap());
    }

    #[test]
    fn test_divisors_sum_type() {
        use DivSum::{Deficient, Perfect, Abundant};
        assert_eq!(divisors_sum_type(12), Abundant);
        assert_eq!(divisors_sum_type(28), Perfect);
        assert_eq!(divisors_sum_type(31), Deficient);
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
    fn test_gcf() {
        assert_eq!(gcf(124, 234), 2);
        assert_eq!(gcf(852, 67344), 12);
        assert_eq!(gcf(52, 512345), 1);
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
}
