use crate::gcf::*;

use std::fmt::Debug;
extern crate num_bigint;
use num_bigint::BigInt;

pub struct Fraction<T> {
    pub numerator: T,
    pub denominator: T,
}

impl<T> Fraction<T> {
    pub fn new(top: T, bottom: T) -> Fraction<T> {
        Fraction{numerator: top, denominator: bottom}
    }
}

impl<T: Copy> Fraction<T> {
    pub fn invert(&mut self) {
        let temp = self.numerator;
        self.numerator = self.denominator;
        self.denominator = temp;
    }
}

impl<T: Clone> Fraction<T> {
    pub fn invert_clone(&mut self) {
        let temp = self.numerator.clone();
        self.numerator = self.denominator.clone();
        self.denominator = temp;
    }
}

impl<T: Debug> std::fmt::Display for Fraction<T> {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        write!(f, "{:?} / {:?}", self.numerator, self.denominator)
    }
}

impl Fraction<i64> {
    pub fn val(&self) -> f64 {
        self.numerator as f64 / self.denominator as f64
    }
    pub fn reduce(&mut self) {
        let gcf: i64 = gcf(&(self.numerator as u64), &(self.denominator as u64)) as i64;
        self.numerator /= gcf;
        self.denominator /= gcf;
    }
}

impl Fraction<BigInt> {
    pub fn reduce(&mut self) {
        let gcf = gcf(&self.numerator, &self.denominator);
        self.numerator /= &gcf;
        self.denominator /= &gcf;
    }
}

// TODO tests
