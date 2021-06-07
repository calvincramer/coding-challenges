use std::time::Instant;

          mod p001; mod p002; mod p003; mod p004; mod p005; mod p006; mod p007; mod p008; mod p009;
mod p010; mod p011; mod p012; mod p013; mod p014; mod p015; mod p016; mod p017; mod p018; mod p019;
mod p020; mod p021; mod p022; mod p023; mod p024; mod p025; mod p026; mod p027; mod p028; mod p029;
mod p030; mod p031; mod p032; mod p033; mod p034; mod p035; mod p036; mod p037; mod p038; mod p039;
mod p040; mod p041; mod p042; mod p043; mod p044; mod p045; mod p046; mod p047; mod p048; mod p049;
mod p050; mod p051; mod p052; mod p053; mod p054; mod p055; mod p056; mod p057; mod p058; mod p059;
mod p060; mod p061; mod p062; mod p063; mod p064; mod p065; mod p066; mod p067; mod p068; mod p069;
mod p070; mod p071; mod p072; mod p073; mod p074; mod p075; mod p076; mod p077; mod p078; mod p079;
mod p080; mod p081; mod p082; mod p083;

// Each problem module should implement this
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

fn timed_run(prob: &dyn Problem, verbose: bool, real_answer: String) {
    let start = Instant::now();
    let (problem_num, answer_desc, answer_str) = prob.run(verbose);
    let mut answer_str_print = answer_str.clone();
    if answer_str != real_answer {
        answer_str_print += " WRONG ANSWER";
    }
    print!("{:>w1$} | {:>w2$} | {:w3$} | {:.5}",
           problem_num,
           answer_desc,
           answer_str_print,
           start.elapsed().as_secs_f64(), w1=C1_WIDTH, w2=C2_WIDTH, w3=C3_WIDTH);
    if answer_str != real_answer {
        print!("\t\tGot {}, expected {}", answer_str, real_answer);
    }
    println!();
}

fn run_problems(verbose: bool) {
    // timed_run(&p001::P001{}, verbose, "233168".to_string());
    // timed_run(&p002::P002{}, verbose, "4613732".to_string());
    // timed_run(&p003::P003{}, verbose, "6857".to_string());
    // timed_run(&p004::P004{}, verbose, "906609".to_string());
    // timed_run(&p005::P005{}, verbose, "232792560".to_string());
    // timed_run(&p006::P006{}, verbose, "25164150".to_string());
    // timed_run(&p007::P007{}, verbose, "104743".to_string());
    // timed_run(&p008::P008{}, verbose, "23514624000".to_string());
    // timed_run(&p009::P009{}, verbose, "31875000".to_string());
    // timed_run(&p010::P010{}, verbose, "142913828922".to_string());
    // timed_run(&p011::P011{}, verbose, "70600674".to_string());
    // timed_run(&p012::P012{}, verbose, "76576500".to_string());
    // timed_run(&p013::P013{}, verbose, "5537376230".to_string());
    // timed_run(&p014::P014{}, verbose, "837799".to_string());
    // timed_run(&p015::P015{}, verbose, "137846528820".to_string());
    // timed_run(&p016::P016{}, verbose, "1366".to_string());
    // timed_run(&p017::P017{}, verbose, "21124".to_string());
    // timed_run(&p018::P018{}, verbose, "1074".to_string());
    // timed_run(&p019::P019{}, verbose, "171".to_string());
    // timed_run(&p020::P020{}, verbose, "648".to_string());
    // timed_run(&p021::P021{}, verbose, "31626".to_string());
    // timed_run(&p022::P022{}, verbose, "871198282".to_string());
    // timed_run(&p023::P023{}, verbose, "4179871".to_string());
    // timed_run(&p024::P024{}, verbose, "2783915460".to_string());
    // timed_run(&p025::P025{}, verbose, "4782".to_string());
    // timed_run(&p026::P026{}, verbose, "983".to_string());
    // timed_run(&p027::P027{}, verbose, "-59231".to_string());
    // timed_run(&p028::P028{}, verbose, "669171001".to_string());
    // timed_run(&p029::P029{}, verbose, "9183".to_string());
    // timed_run(&p030::P030{}, verbose, "443839".to_string());
    // timed_run(&p031::P031{}, verbose, "73682".to_string());
    // timed_run(&p032::P032{}, verbose, "45228".to_string());
    // timed_run(&p033::P033{}, verbose, "100".to_string());
    // timed_run(&p034::P034{}, verbose, "40730".to_string());
    // timed_run(&p035::P035{}, verbose, "55".to_string());
    // timed_run(&p036::P036{}, verbose, "872187".to_string());
    // timed_run(&p037::P037{}, verbose, "748317".to_string());
    // timed_run(&p038::P038{}, verbose, "932718654".to_string());
    // timed_run(&p039::P039{}, verbose, "840".to_string());
    // timed_run(&p040::P040{}, verbose, "210".to_string());
    // timed_run(&p041::P041{}, verbose, "7652413".to_string());
    // timed_run(&p042::P042{}, verbose, "162".to_string());
    // timed_run(&p043::P043{}, verbose, "16695334890".to_string());
    // timed_run(&p044::P044{}, verbose, "5482660".to_string());
    // timed_run(&p045::P045{}, verbose, "1533776805".to_string());
    // timed_run(&p046::P046{}, verbose, "5777".to_string());
    // timed_run(&p047::P047{}, verbose, "134043".to_string());
    // timed_run(&p048::P048{}, verbose, "9110846700".to_string());
    // timed_run(&p049::P049{}, verbose, "296962999629".to_string());
    // // timed_run(&p050::P050{}, verbose, "997651".to_string());  // Slow (in debug only)
    // // timed_run(&p051::P051{}, verbose, "121313".to_string());  // Slow (in debug only)
    // timed_run(&p052::P052{}, verbose, "142857".to_string());
    // timed_run(&p053::P053{}, verbose, "4075".to_string());
    // timed_run(&p054::P054{}, verbose, "376".to_string());
    // timed_run(&p055::P055{}, verbose, "249".to_string());
    // timed_run(&p056::P056{}, verbose, "972".to_string());
    // timed_run(&p057::P057{}, verbose, "153".to_string());
    // timed_run(&p058::P058{}, verbose, "26241".to_string());
    // timed_run(&p059::P059{}, verbose, "129448".to_string());
    // timed_run(&p060::P060{}, verbose, "26033".to_string());
    // timed_run(&p061::P061{}, verbose, "28684".to_string());
    // timed_run(&p062::P062{}, verbose, "127035954683".to_string());
    // timed_run(&p063::P063{}, verbose, "49".to_string());
    // timed_run(&p064::P064{}, verbose, "1322".to_string());
    // timed_run(&p065::P065{}, verbose, "272".to_string());
    // // timed_run(&p066::P066{}, verbose, "???".to_string());
    // timed_run(&p067::P067{}, verbose, "7273".to_string());
    // timed_run(&p068::P068{}, verbose, "6531031914842725".to_string());
    // timed_run(&p069::P069{}, verbose, "510510".to_string());
    // // timed_run(&p070::P070{}, verbose, "8319823".to_string());    // Slow
    // timed_run(&p071::P071{}, verbose, "428570".to_string());
    // timed_run(&p072::P072{}, verbose, "303963552391".to_string());
    // timed_run(&p073::P073{}, verbose, "7295372".to_string());
    // timed_run(&p074::P074{}, verbose, "402".to_string());
    // // timed_run(&p075::P075{}, verbose, "???".to_string()); too slow
    // timed_run(&p076::P076{}, verbose, "190569291".to_string());
    // timed_run(&p077::P077{}, verbose, "71".to_string());
    // timed_run(&p078::P078{}, verbose, "55374".to_string());
    // timed_run(&p079::P079{}, verbose, "73162890".to_string());
    // // timed_run(&p080::P080{}, verbose, "unknown".to_string());
    // timed_run(&p081::P081{}, verbose, "427337".to_string());
    // timed_run(&p082::P082{}, verbose, "260324".to_string());
    timed_run(&p083::P083{}, verbose, "425185".to_string());
}

fn main() {
    timed_run_header();
    let start = Instant::now();
    run_problems(false);
    println!("\nTotal time: {:.5}s", start.elapsed().as_secs_f64());();
}
