/// Matches a roman numeral character to its decimal value. Panics on invalid characters
pub fn roman_char_value(c: u8) -> u64 {
    match c as char {
        'I' => 1,
        'V' => 5,
        'X' => 10,
        'L' => 50,
        'C' => 100,
        'D' => 500,
        'M' => 1000,
        _ => panic!("{} is not a valid roman numeral character", c),
    }
}

/// Converts a roman numberal to a number
pub fn roman_to_numeric(roman_numeral: &[u8]) -> u64 {
    let mut num = 0u64;
    let mut i = 0;
    while i < roman_numeral.len() - 1 {
        let i_val = roman_char_value(roman_numeral[i]);
        let i_val_next = roman_char_value(roman_numeral[i + 1]);
        num += match i_val >= i_val_next {
            true => {
                i += 1;
                i_val
            }
            false => {
                i += 2;
                i_val_next - i_val
            }
        };
    }
    // Last character?
    while i < roman_numeral.len() {
        num += roman_char_value(roman_numeral[i]);
        i += 1;
    }
    num
}

/// Converts a number to roman numeral form (with the least amount of characters possible)
#[rustfmt::skip]
pub fn numeric_to_roman(mut num: u64) -> String {
    let mut r_str = String::new();
    while num >= 1000 {   r_str += "M";   num -= 1000;    }
    while num >= 900 {    r_str += "CM";  num -= 900;     }
    while num >= 500 {    r_str += "D";   num -= 500;     }
    while num >= 400 {    r_str += "CD";  num -= 400;     }
    while num >= 100 {    r_str += "C";   num -= 100;     }
    while num >= 90 {     r_str += "XC";  num -= 90;      }
    while num >= 50 {     r_str += "L";   num -= 50;      }
    while num >= 40 {     r_str += "XL";  num -= 40;      }
    while num >= 10 {     r_str += "X";   num -= 10;      }
    while num >= 9 {      r_str += "IX";  num -= 9;       }
    while num >= 5 {      r_str += "V";   num -= 5;       }
    while num >= 4 {      r_str += "IV";  num -= 4;       }
    while num >= 1 {      r_str += "I";   num -= 1;       }
    r_str
}

/// Takes a valid roman numeric and reduces it to a roman numeral using the least amount of characters
pub fn reduce_roman(roman_numeral: &String) -> String {
    numeric_to_roman(roman_to_numeric(roman_numeral.as_bytes()))
}
