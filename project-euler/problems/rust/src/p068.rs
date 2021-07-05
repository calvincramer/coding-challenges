pub struct P068 {}
impl crate::Problem for P068 {
    #[rustfmt::skip]
    #[allow(unused_variables)]
    fn solve(&self, verbose: bool) -> String {
        const MAX: u16 = 10;
        let mut max = u64::MIN;
        for n0 in 1..=MAX {
        for n1 in (n0+1)..=MAX {
            if n1 == n0 { continue; }
        for n2 in (n0+1)..=MAX {
            if n2 == n0 || n2 == n1 { continue; }
        for n3 in (n0+1)..=MAX {
            if n3 == n0 || n3 == n1 || n3 == n2 { continue; }
        for n4 in (n0+1)..=MAX {
            if n4 == n0 || n4 == n1 || n4 == n2 || n4 == n3 { continue; }
        for n5 in 1..=MAX {
            if n5 == n0 || n5 == n1 || n5 == n2 || n5 == n3 || n5 == n4 { continue; }
        for n6 in 1..=MAX {
            if n6 == n0 || n6 == n1 || n6 == n2 || n6 == n3 || n6 == n4 || n6 == n5 { continue; }
        for n7 in 1..=MAX {
            if n7 == n0 || n7 == n1 || n7 == n2 || n7 == n3 || n7 == n4 || n7 == n5 || n7 == n6 { continue; }
        for n8 in 1..=MAX {
            if n8 == n0 || n8 == n1 || n8 == n2 || n8 == n3 || n8 == n4 || n8 == n5 || n8 == n6 || n8 == n7 { continue; }
        for n9 in 1..=MAX {
            if n9 == n0 || n9 == n1 || n9 == n2 || n9 == n3 || n9 == n4 || n9 == n5 || n9 == n6 || n9 == n7 || n9 == n8 { continue; }

            let total0 = n0 + n5 + n6;
            let total1 = n1 + n6 + n7;
            if total1 != total0 { continue; }
            let total2 = n2 + n7 + n8;
            if total2 != total0 { continue; }
            let total3 = n3 + n8 + n9;
            if total3 != total0 { continue; }
            let total4 = n4 + n9 + n5;
            if total4 != total0 { continue; }

            assert!(total0 == total1 && total1 == total2 && total2 == total3 && total3 == total4 && total4 == total0);
            assert!(n0 < n1 && n0 < n2 && n0 < n3 && n0 < n4);

            let big_num = format!("{}{}{}{}{}{}{}{}{}{}{}{}{}{}{}", n0,n5,n6, n1,n6,n7, n2,n7,n8, n3,n8,n9, n4,n9,n5).parse::<u64>().unwrap();
            if verbose { println!("{}", big_num); }
            if big_num <= 9_999_999_999_999_999 {   // 16-digit string
                max = std::cmp::max(max, big_num);
            }
        }}}}}}}}}}
        max.to_string()
    }
    fn is_slow(&self) -> bool { false }
    fn problem_num(&self) -> i32 { 68 }
    fn answer_desc(&self) -> String { "Max".to_string() }
    fn real_answer(&self) -> crate::ProblemAnswer { crate::ProblemAnswer::Some("6531031914842725".to_string()) }
}
