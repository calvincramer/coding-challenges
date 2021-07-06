/// Number to english string representation
/// I know it looks ugly. Still getting used to strings in Rust.
pub fn num_to_string(num: i64) -> String {
    if num == 0 {
        return "zero".to_string();
    }
    let table = [
        "", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine",
    ];
    let table_tens = [
        "", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety",
    ];
    let table_special = [
        "ten",
        "eleven",
        "twelve",
        "thirteen",
        "fourteen",
        "fifteen",
        "sixteen",
        "seventeen",
        "eighteen",
        "nineteen",
    ];

    let _thou_to_str = |n: u16| {
        let digit_1 = n % 10;
        let digit_10 = (n / 10) % 10;
        let digit_100 = (n / 100) % 10;
        let mut hundreds = format!(
            "{}{}",
            table[(n / 100) as usize],
            if digit_100 > 0 { " hundred " } else { "" }
        );
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
                    table_tens[digit_10 as usize].to_string()
                        + &"-".to_string()
                        + &table[digit_1 as usize].to_string()
                } else {
                    table_tens[digit_10 as usize].to_string() + &table[digit_1 as usize].to_string()
                }
            }
            _ => panic!("bad"),
        };
        hundreds + &rest
    };

    let negative = if num < 0 { true } else { false };
    let mut num_s = num.abs().to_string();
    let mut endings_i = 0;
    let endings = [
        "",
        "thousand",
        "million",
        "billion",
        "trillion",
        "quadrillion",
        "quintillion",
        "sextillion",
        "septillion",
        "octillion",
        "nonillion",
        "decillion",
        "undecillion",
        "duodecillion",
        "tredecillion",
        "quattuordecillion",
        "quindecillion",
        "sexdecillion",
        "septendecillion",
        "octodecillion",
        "novemdecillion",
        "vigintillion",
    ];
    let mut s = String::new();
    loop {
        let temp_3digs = if num_s.len() >= 3 {
            num_s[num_s.len() - 3..].parse::<u16>().unwrap()
        } else {
            num_s.parse::<u16>().unwrap()
        };
        s = format!(
            "{} {} {}",
            _thou_to_str(temp_3digs),
            endings[endings_i].to_string(),
            s
        );
        endings_i += 1;
        num_s = if num_s.len() >= 3 {
            num_s[..num_s.len() - 3].to_string()
        } else {
            "".to_string()
        };
        if !(num_s.len() > 0 && endings_i < endings.len()) {
            break;
        }
    }
    if num_s.len() > 0 {
        s = format!("SOME BIGGER NUMBERS AND {}", s);
    }
    if negative {
        s = format!("negative {}", s);
    }
    s.trim_end().to_string()
}

#[cfg(test)]
mod lib_rs_tests {
    use super::*;

    #[test]
    fn test_num_to_string() {
        assert_eq!(num_to_string(0), "zero");
        assert_eq!(num_to_string(5), "five");
        assert_eq!(num_to_string(14), "fourteen");
        assert_eq!(num_to_string(-14), "negative fourteen");
        assert_eq!(num_to_string(30), "thirty");
        assert_eq!(num_to_string(-511), "negative five hundred and eleven");
        assert_eq!(
            num_to_string(123456),
            "one hundred and twenty-three thousand four hundred and fifty-six"
        );
    }
}
