/// Main implementation for Palindrome trait
fn _impl_is_palindrome_string(s: &String) -> bool {
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
    fn is_palindrome(&self) -> bool;
}
impl Palindrome for u64 {
    fn is_palindrome(&self) -> bool {
        _impl_is_palindrome_string(&self.to_string())
    }
}
impl Palindrome for i32 {
    fn is_palindrome(&self) -> bool {
        match self {
            _ if *self < 0 => panic!("Have not implement palindrome for negative numbers!"),
            _ => _impl_is_palindrome_string(&self.to_string()),
        }
    }
}
impl Palindrome for String {
    fn is_palindrome(&self) -> bool {
        _impl_is_palindrome_string(self)
    }
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
