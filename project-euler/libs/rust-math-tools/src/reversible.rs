use std::fmt::Debug;
use std::str::FromStr;
use std::string::ToString;

pub fn reverse<T>(thing: &T) -> T
where
    T: ToString + FromStr,
    <T as FromStr>::Err: Debug,
{
    thing
        .to_string()
        .chars()
        .rev()
        .collect::<String>()
        .parse::<T>()
        .unwrap()
}

#[test]
fn test_reversible() {
    extern crate num_bigint;
    use num_bigint::BigUint;
    // u64
    assert_eq!(reverse(&1234u64), 4321u64);
    // BigUInt
    assert_eq!(reverse(&BigUint::from(97531u64)), BigUint::from(13579u64));
}
