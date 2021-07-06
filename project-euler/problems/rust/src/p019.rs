fn is_leap_year(year: u64) -> bool {
    match year {
        _ if year % 4 == 0 && year % 100 != 0 => true,
        _ if year % 400 == 0 => true,
        _ => false,
    }
}

#[derive(Debug, PartialEq, Eq)]
enum Day {
    MON,
    TUE,
    WED,
    THU,
    FRI,
    SAT,
    SUN,
}

fn next_day(day: &Day) -> Day {
    match day {
        Day::MON => Day::TUE,
        Day::TUE => Day::WED,
        Day::WED => Day::THU,
        Day::THU => Day::FRI,
        Day::FRI => Day::SAT,
        Day::SAT => Day::SUN,
        Day::SUN => Day::MON,
    }
}

#[derive(Debug, PartialEq, Eq)]
enum Month {
    JAN,
    FEB,
    MAR,
    APR,
    MAY,
    JUN,
    JUL,
    AUG,
    SEP,
    OCT,
    NOV,
    DEC,
}

fn next_month(month: &Month) -> Month {
    match month {
        Month::JAN => Month::FEB,
        Month::FEB => Month::MAR,
        Month::MAR => Month::APR,
        Month::APR => Month::MAY,
        Month::MAY => Month::JUN,
        Month::JUN => Month::JUL,
        Month::JUL => Month::AUG,
        Month::AUG => Month::SEP,
        Month::SEP => Month::OCT,
        Month::OCT => Month::NOV,
        Month::NOV => Month::DEC,
        Month::DEC => Month::JAN,
    }
}

fn days_in(month: &Month, year: u64) -> u64 {
    match month {
        Month::JAN => 31,
        Month::FEB => {
            if is_leap_year(year) {
                29
            } else {
                28
            }
        }
        Month::MAR => 31,
        Month::APR => 30,
        Month::MAY => 31,
        Month::JUN => 30,
        Month::JUL => 31,
        Month::AUG => 31,
        Month::SEP => 30,
        Month::OCT => 31,
        Month::NOV => 30,
        Month::DEC => 31,
    }
}

#[derive(Debug)]
struct Date {
    day_of_week: Day,
    day: u64,
    // Day in month
    month: Month,
    year: u64,
}

impl Date {
    fn next(&mut self) {
        self.day_of_week = next_day(&self.day_of_week); // Keep on wrapping day of week, it has no affect on the other parts
        self.day += 1;
        if self.day > days_in(&self.month, self.year) {
            // Spill over next month
            self.day = 1;
            self.month = next_month(&self.month);
            if self.month == Month::JAN {
                // Spill over year
                self.year += 1;
            }
        }
    }
    fn is_sunday_1(&self) -> bool {
        self.day_of_week == Day::SUN && self.day == 1
    }
}

pub struct P019 {}
impl crate::Problem for P019 {
    #[allow(unused_variables)]
    fn solve(&self, verbose: bool) -> String {
        let mut date = Date {
            day_of_week: Day::MON,
            day: 1,
            month: Month::JAN,
            year: 1900,
        };
        let mut total = 0;
        loop {
            if date.is_sunday_1() && date.year >= 1901 {
                total += 1;
            }
            if date.year > 2000 {
                break;
            } else {
                date.next();
            }
        }
        total.to_string()
    }
    fn is_slow(&self) -> bool {
        false
    }
    fn problem_num(&self) -> i32 {
        19
    }
    fn answer_desc(&self) -> String {
        "Answer".to_string()
    }
    fn real_answer(&self) -> crate::ProblemAnswer {
        crate::ProblemAnswer::Some("171".to_string())
    }
}
