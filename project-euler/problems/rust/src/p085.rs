#[allow(dead_code)]
fn count_rects(w: usize, h: usize) -> isize {
    let mut count = 0;
    for y in 1..=h {
        for x in 1..=w {
            count += (w - x + 1) * (h - y + 1);
        }
    }
    count as isize
}

/// Same as count_rects(), but solved discrete sum
fn count_rects_2(w: usize, h: usize) -> isize {
    ((w * (w + 1) * h * (h + 1)) / 4) as isize
}

pub struct P085 {}
impl crate::Problem for P085 {
    fn solve(&self, verbose: bool) -> String {
        const GOAL: isize = 2_000_000;
        const MAX: usize = 100; // Found limit by running
        let mut closest_w = 0;
        let mut closest_h = 0;
        let mut closest_num = 0;

        for width in 1..=MAX {
            for height in 1..=MAX {
                let num_rects = count_rects_2(width, height);
                if (num_rects - GOAL).abs() < (closest_num - GOAL).abs() {
                    closest_num = num_rects;
                    closest_h = height;
                    closest_w = width;
                    if verbose {
                        println!("w={} h={} num={}", width, height, num_rects);
                    }
                }
            }
        }
        if verbose {
            println!("Final w={} h={} num={}", closest_w, closest_h, closest_num);
        }
        (closest_h * closest_w).to_string()
    }
    fn is_slow(&self) -> bool {
        false
    }
    fn problem_num(&self) -> i32 {
        85
    }
    fn answer_desc(&self) -> String {
        "Rect area".to_string()
    }
    fn real_answer(&self) -> crate::ProblemAnswer {
        crate::ProblemAnswer::Some("2772".to_string())
    }
}
