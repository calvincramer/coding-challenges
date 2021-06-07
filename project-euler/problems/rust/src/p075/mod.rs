use num_integer::sqrt;

// TODO too slow

pub struct P075 {}
impl crate::Problem for P075 {
    #[allow(unused_variables)]
    fn solve(&self, verbose: bool) -> String {
        const MAX: usize = 15_000_001;
        let mut triangles = vec![0; MAX];

        for a in 1..MAX {
        for b in (a+1)..MAX {
            let c = sqrt(a*a + b*b);
            if a*a + b*b != c*c { continue; }
            let len = a+b+c;
            if len >= MAX { continue; }
            triangles[len] += 1;
        }}

        let total = triangles.iter().filter(|t| **t == 1).count();

        total.to_string()
    }
    fn is_slow(&self) -> bool { false }
    fn problem_num(&self) -> i32 { 75 }
    fn answer_desc(&self) -> String { "Answer".to_string() }
    fn real_answer(&self) -> crate::ProblemAnswer { crate::ProblemAnswer::AnswerUnknown }
}
