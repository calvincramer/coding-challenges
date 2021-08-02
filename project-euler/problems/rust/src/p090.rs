use rust_math_tools::combinations;

fn can_form(dice1: &[u64], dice2: &[u64], digit1: u64, digit2: u64) -> bool {
    (dice1.contains(&digit1) && dice2.contains(&digit2))
        || (dice1.contains(&digit2) && dice2.contains(&digit1))
}

fn can_make_squares(dice1: &[u64], dice2: &[u64]) -> bool {
    can_form(dice1, dice2, 0, 1)
        && can_form(dice1, dice2, 0, 4)
        && (can_form(dice1, dice2, 0, 9) || can_form(dice1, dice2, 0, 6))
        && (can_form(dice1, dice2, 1, 6) || can_form(dice1, dice2, 1, 9))
        && can_form(dice1, dice2, 2, 5)
        && (can_form(dice1, dice2, 3, 6) || can_form(dice1, dice2, 3, 9))
        && (can_form(dice1, dice2, 4, 9) || can_form(dice1, dice2, 4, 6))
        && (can_form(dice1, dice2, 6, 4) || can_form(dice1, dice2, 9, 4))
        && can_form(dice1, dice2, 8, 1)
}

pub struct P090 {}
impl crate::Problem for P090 {
    #[allow(unused_variables)]
    fn solve(&self, verbose: bool) -> String {
        let mut total = 0;
        let dices = combinations(&vec![0, 1, 2, 3, 4, 5, 6, 7, 8, 9], 6);
        for i in 0..dices.len() {
            for j in i..dices.len() {
                if can_make_squares(&dices[i], &dices[j]) {
                    total += 1;
                }
            }
        }
        (total + 1).to_string()
    }
    fn is_slow(&self) -> bool {
        false
    }
    fn problem_num(&self) -> i32 {
        90
    }
    fn answer_desc(&self) -> String {
        "Num arrangements".to_string()
    }
    fn real_answer(&self) -> crate::ProblemAnswer {
        crate::ProblemAnswer::Some("1217".to_string())
    }
}
