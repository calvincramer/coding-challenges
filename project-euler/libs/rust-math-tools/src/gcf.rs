extern crate num_traits;
use num_traits::Zero;
use std::cmp::Eq;
use std::ops::Rem;

/// Calculates greatest common factor between two numbers
pub fn gcf<T>(n1: &T, n2: &T) -> T
where
    T: Clone + Eq + Rem<Output = T> + Zero,
{
    let mut n1_temp = n1.clone();
    let mut n2_temp = n2.clone();
    if n1_temp == T::zero() {
        return n2_temp;
    }
    while n2_temp != T::zero() {
        let r = n1_temp.clone() % n2_temp.clone();
        n1_temp = n2_temp;
        n2_temp = r;
    }
    return n1_temp;
}

#[test]
fn test_gcf() {
    assert_eq!(gcf(&124, &234), 2);
    assert_eq!(gcf(&852, &67344), 12);
    assert_eq!(gcf(&52, &512345), 1);
}
