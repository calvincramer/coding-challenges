use rayon::prelude::*;

pub struct P091 {}
impl crate::Problem for P091 {
    #[allow(unused_variables)]
    fn solve(&self, verbose: bool) -> String {
        use std::cmp::max;
        const SIZE: isize = 50;
        let right_tris = (0..=SIZE)
            .into_par_iter()
            .map(|p1y| {
                let mut total = 0u64;
                for p1x in 0..=SIZE {
                    if p1y + p1x == 0 {
                        // skip if 0,0
                        continue;
                    }
                    for p2y in 0..=SIZE {
                        for p2x in 0..=SIZE {
                            if p2y + p2x == 0 {
                                // skip if 0,0
                                continue;
                            }
                            if p1y == p2y && p1x == p2x {
                                continue;
                            }
                            // Right triangle?
                            let l1_sq = p1y.pow(2) + p1x.pow(2); // origin to p1
                            let l2_sq = p2y.pow(2) + p2x.pow(2); // origin to p2
                            let d_sq = (p2y - p1y).pow(2) + (p2x - p1x).pow(2); // p1 to p2
                            let max_side_sq = max(l1_sq, max(l2_sq, d_sq));
                            // a^2 + b^2 == c^2, with c being max side.
                            if l1_sq + l2_sq + d_sq - max_side_sq == max_side_sq {
                                total += 1;
                            }
                        }
                    }
                }
                total
            })
            .sum::<u64>();
        (right_tris / 2).to_string()
    }
    fn is_slow(&self) -> bool {
        false
    }
    fn problem_num(&self) -> i32 {
        91
    }
    fn answer_desc(&self) -> String {
        "Num triangles".to_string()
    }
    fn real_answer(&self) -> crate::ProblemAnswer {
        crate::ProblemAnswer::Some("14234".to_string())
    }
}
