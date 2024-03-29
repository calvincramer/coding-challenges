use ansi_term;
use std::fmt;
use std::time::Instant;

mod p001;
mod p002;
mod p003;
mod p004;
mod p005;
mod p006;
mod p007;
mod p008;
mod p009;
mod p010;
mod p011;
mod p012;
mod p013;
mod p014;
mod p015;
mod p016;
mod p017;
mod p018;
mod p019;
mod p020;
mod p021;
mod p022;
mod p023;
mod p024;
mod p025;
mod p026;
mod p027;
mod p028;
mod p029;
mod p030;
mod p031;
mod p032;
mod p033;
mod p034;
mod p035;
mod p036;
mod p037;
mod p038;
mod p039;
mod p040;
mod p041;
mod p042;
mod p043;
mod p044;
mod p045;
mod p046;
mod p047;
mod p048;
mod p049;
mod p050;
mod p051;
mod p052;
mod p053;
mod p054;
mod p055;
mod p056;
mod p057;
mod p058;
mod p059;
mod p060;
mod p061;
mod p062;
mod p063;
mod p064;
mod p065;
mod p066;
mod p067;
mod p068;
mod p069;
mod p070;
mod p071;
mod p072;
mod p073;
mod p074;
mod p075;
mod p076;
mod p077;
mod p078;
mod p079;
mod p080;
mod p081;
mod p082;
mod p083;
mod p084;
mod p085;
mod p086;
mod p087;
mod p088;
mod p089;
mod p090;
mod p091;
mod p092;
mod p093;
mod p094;

#[derive(Debug)]
enum ProblemAnswer {
    Some(String),
    AnswerUnknown,
}
impl fmt::Display for ProblemAnswer {
    fn fmt(&self, f: &mut fmt::Formatter) -> fmt::Result {
        write!(
            f,
            "{}",
            match self {
                ProblemAnswer::Some(s) => s,
                ProblemAnswer::AnswerUnknown => "AnswerUnknown",
            }
        )
    }
}

// Each problem module should implement this
trait Problem {
    fn solve(&self, verbose: bool) -> String;
    fn is_slow(&self) -> bool;
    fn problem_num(&self) -> i32;
    fn answer_desc(&self) -> String;
    fn real_answer(&self) -> ProblemAnswer;
}

const C1_WIDTH: usize = 3;
const C2_WIDTH: usize = 20;
const C3_WIDTH: usize = 20;
const C4_WIDTH: usize = 8;

#[allow(dead_code)]
fn timed_run_header() {
    println!(
        "{:>w1$} | {:^w2$} | {:^w3$} | {:.w4$}",
        "P #",
        "Desc",
        "Answer",
        "Time(s)",
        w1 = C1_WIDTH,
        w2 = C2_WIDTH,
        w3 = C3_WIDTH,
        w4 = C4_WIDTH
    );
    println!(
        "{:->w1$}-|-{:->w2$}-|-{:->w3$}-|-{:->w4$}",
        "",
        "",
        "",
        "",
        w1 = C1_WIDTH,
        w2 = C2_WIDTH,
        w3 = C3_WIDTH,
        w4 = C4_WIDTH
    );
}

/// Returns whether problem passed or failed
#[allow(dead_code)]
fn timed_run_checked(prob: &dyn Problem, verbose: bool, skip_slow: bool) -> bool {
    let mut passed = true;
    let real_answer = prob.real_answer().to_string();
    let skip_run = (skip_slow && prob.is_slow())
        || match prob.real_answer() {
            ProblemAnswer::Some(_) => false,
            ProblemAnswer::AnswerUnknown => true,
        };
    let mut elapsed = 0f64;
    let mut answer_str = "SKIP SLOW / UNKNOWN ".to_string();
    if !skip_run {
        // No multiple assignment :(
        let (tmp_answer_str, tmp_elapsed) = timed_run(prob, verbose);
        answer_str = tmp_answer_str;
        elapsed = tmp_elapsed;
    }
    // Print information:
    let mut to_print = format!(
        "{:>w1$} | {:>w2$} | {:w3$} | {:.5}",
        prob.problem_num(),
        prob.answer_desc(),
        answer_str,
        elapsed,
        w1 = C1_WIDTH,
        w2 = C2_WIDTH,
        w3 = C3_WIDTH
    );
    if answer_str != real_answer && !skip_run {
        to_print += format!(
            "\t\tWrong answer! Got {}, expected {}",
            answer_str, real_answer
        )
        .as_str();
        passed = false;
    }
    to_print += "\n";
    if skip_run {
        print!(
            "{}",
            ansi_term::Style::new().strikethrough().paint(to_print)
        );
    } else if answer_str != real_answer {
        print!("{}", ansi_term::Color::Red.paint(to_print));
    } else {
        print!("{}", to_print);
    }
    return passed;
}

fn timed_run(prob: &dyn Problem, verbose: bool) -> (String, f64) {
    let start = Instant::now();
    (prob.solve(verbose), start.elapsed().as_secs_f64())
}

/// Returned true if all problems passed
#[allow(dead_code)]
#[cfg(feature = "run_all")]
fn run_all_problems() -> bool {
    const VERBOSE: bool = false;
    const SKIP_SLOW: bool = true; // Don't push this to be false.
    let mut passed_all = true;
    #[rustfmt::skip]
    let problems: Vec<&dyn Problem> = vec![
                       &p001::P001{}, &p002::P002{}, &p003::P003{}, &p004::P004{}, &p005::P005{}, &p006::P006{}, &p007::P007{}, &p008::P008{}, &p009::P009{},
        &p010::P010{}, &p011::P011{}, &p012::P012{}, &p013::P013{}, &p014::P014{}, &p015::P015{}, &p016::P016{}, &p017::P017{}, &p018::P018{}, &p019::P019{},
        &p020::P020{}, &p021::P021{}, &p022::P022{}, &p023::P023{}, &p024::P024{}, &p025::P025{}, &p026::P026{}, &p027::P027{}, &p028::P028{}, &p029::P029{},
        &p030::P030{}, &p031::P031{}, &p032::P032{}, &p033::P033{}, &p034::P034{}, &p035::P035{}, &p036::P036{}, &p037::P037{}, &p038::P038{}, &p039::P039{},
        &p040::P040{}, &p041::P041{}, &p042::P042{}, &p043::P043{}, &p044::P044{}, &p045::P045{}, &p046::P046{}, &p047::P047{}, &p048::P048{}, &p049::P049{},
        &p050::P050{}, &p051::P051{}, &p052::P052{}, &p053::P053{}, &p054::P054{}, &p055::P055{}, &p056::P056{}, &p057::P057{}, &p058::P058{}, &p059::P059{},
        &p060::P060{}, &p061::P061{}, &p062::P062{}, &p063::P063{}, &p064::P064{}, &p065::P065{}, &p066::P066{}, &p067::P067{}, &p068::P068{}, &p069::P069{},
        &p070::P070{}, &p071::P071{}, &p072::P072{}, &p073::P073{}, &p074::P074{}, &p075::P075{}, &p076::P076{}, &p077::P077{}, &p078::P078{}, &p079::P079{}, 
        &p080::P080{}, &p081::P081{}, &p082::P082{}, &p083::P083{}, &p084::P084{}, &p085::P085{}, &p086::P086{}, &p087::P087{}, &p088::P088{}, &p089::P089{},
        &p090::P090{}, &p091::P091{}, &p092::P092{}, &p093::P093{},
    ];
    timed_run_header();
    let start = Instant::now();
    for prob in problems {
        passed_all = passed_all & timed_run_checked(prob, VERBOSE, SKIP_SLOW);
    }
    println!("\nTotal time: {:.5}s", start.elapsed().as_secs_f64());
    passed_all
}

#[cfg(feature = "run_one")]
fn run_specific_problem() {
    let problem = p094::P094 {};
    println!("Problem #{}", problem.problem_num());
    let (answer_str, elapsed) = timed_run(&problem, true);
    println!("Answer: {}", answer_str);
    println!("Elapsed: {}s", elapsed);
}

fn main() -> Result<(), ()> {
    if cfg!(feature = "run_all") {
        match run_all_problems() {
            true => Ok(()),
            false => Err(()),
        }
    } else if cfg!(feature = "run_one") {
        run_specific_problem();
        Ok(())
    } else {
        Ok(())
    }
}
