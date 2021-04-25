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

// Each problem should implement this
trait Problem {
    /// Run the problem
    /// Returns the problem number, answer description (such as "Sum"), and answer (such as "1234")
    fn run(&self, verbose: bool) -> (i32, String, String);
}

const C1_WIDTH: usize = 3;
const C2_WIDTH: usize = 20;
const C3_WIDTH: usize = 20;
const C4_WIDTH: usize = 8;

fn timed_run_header() {
    println!("{:>w1$} | {:^w2$} | {:^w3$} | {:.w4$}", "P #", "Desc", "Answer", "Time(s)", w1=C1_WIDTH, w2=C2_WIDTH, w3=C3_WIDTH, w4=C4_WIDTH);
    println!("{:->w1$}-|-{:->w2$}-|-{:->w3$}-|-{:->w4$}", "", "", "", "", w1=C1_WIDTH, w2=C2_WIDTH, w3=C3_WIDTH, w4=C4_WIDTH);
}

fn timed_run(prob: &dyn Problem, verbose: bool) {
    let start = Instant::now();
    let (problem_num, answer_desc, answer_str) = prob.run(verbose);
    println!("{:>w1$} | {:>w2$} | {:w3$} | {:.3}",
             problem_num, answer_desc, answer_str, start.elapsed().as_secs_f64(), w1=C1_WIDTH, w2=C2_WIDTH, w3=C3_WIDTH);
}

fn main() {
    timed_run_header();
    timed_run(&p001::P001{}, false);
    timed_run(&p002::P002{}, false);
    timed_run(&p003::P003{}, false);
    timed_run(&p004::P004{}, false);
    timed_run(&p005::P005{}, false);
    timed_run(&p006::P006{}, false);
    timed_run(&p007::P007{}, false);
    timed_run(&p008::P008{}, false);
    timed_run(&p009::P009{}, false);
    timed_run(&p010::P010{}, false);
    timed_run(&p011::P011{}, false);
    timed_run(&p012::P012{}, false);
    timed_run(&p013::P013{}, false);
    timed_run(&p014::P014{}, false);
    timed_run(&p015::P015{}, false);
    timed_run(&p016::P016{}, false);
    timed_run(&p017::P017{}, false);
    timed_run(&p018::P018{}, false);
    timed_run(&p019::P019{}, false);
    timed_run(&p020::P020{}, false);
    timed_run(&p021::P021{}, false);
    timed_run(&p022::P022{}, false);
    timed_run(&p023::P023{}, false);
    timed_run(&p024::P024{}, false);
    timed_run(&p025::P025{}, false);
    timed_run(&p026::P026{}, false);
    timed_run(&p027::P027{}, false);
    timed_run(&p028::P028{}, false);
    timed_run(&p029::P029{}, false);
    timed_run(&p030::P030{}, false);
    timed_run(&p031::P031{}, false);
    timed_run(&p032::P032{}, false);
    timed_run(&p033::P033{}, false);
    timed_run(&p034::P034{}, false);
    timed_run(&p035::P035{}, false);
    timed_run(&p036::P036{}, false);
}