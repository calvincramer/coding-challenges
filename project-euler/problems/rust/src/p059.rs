use rust_math_tools::read_all_lines;

fn decrypt(cipher_text: &Vec<u8>, password: &[u8]) -> String {
    let mut result = String::new();
    let mut password_i = 0;
    for c in cipher_text {
        result.push((c ^ password[password_i]) as char);
        password_i = (password_i + 1) % password.len();
    }
    result
}

fn is_real_message(message: &String) -> bool {
    message.contains(" the") && message.contains(" and") && message.contains(" in")
}

pub struct P059 {}
impl crate::Problem for P059 {
    #[rustfmt::skip]
    fn solve(&self, verbose: bool) -> String {
        let cipher_str = read_all_lines("src/input_files/p059_cipher.txt".to_string());
        let cipher_str: Vec<&str> = cipher_str[0].split(',').collect();
        let cipher_text: Vec<u8> = cipher_str.iter().map(|s| s.parse::<u8>().unwrap()).collect();
        let mut passwd = [0, 0, 0];

        for c0 in 97..=122 {    // a to z
        for c1 in 97..=122 {
        for c2 in 97..=122 {
            passwd[0] = c0;
            passwd[1] = c1;
            passwd[2] = c2;
            let plain_text = decrypt(&cipher_text, &passwd);
            if is_real_message(&plain_text) {
                if verbose {
                    println!("Password: {:?}", passwd.iter().map(|n| *n as char).collect::<Vec<char>>());
                    println!("Message: {}", plain_text);
                }
                let s = plain_text.as_bytes().iter().map(|n| *n as u64).sum::<u64>();
                return s.to_string();
            }
        }}}

        "ERROR NO MATCH".to_string()
    }
    fn is_slow(&self) -> bool {
        false
    }
    fn problem_num(&self) -> i32 {
        59
    }
    fn answer_desc(&self) -> String {
        "Sum ASCII".to_string()
    }
    fn real_answer(&self) -> crate::ProblemAnswer {
        crate::ProblemAnswer::Some("129448".to_string())
    }
}
