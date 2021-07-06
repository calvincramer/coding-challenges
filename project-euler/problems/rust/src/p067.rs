use rust_math_tools::read_all_lines;

pub struct P067 {}
impl crate::Problem for P067 {
    #[allow(unused_variables)]
    fn solve(&self, verbose: bool) -> String {
        let lines = read_all_lines("src/input_files/p067_triangle.txt".to_string());
        let mut triangle = Vec::<Vec<u32>>::new();
        for line in lines {
            triangle.push(line.split(" ").map(|s| s.parse::<u32>().unwrap()).collect())
        }

        // First pass, go up triangle (finds max path value)
        for row_i in (0..triangle.len() - 1).rev() {
            for col_i in 0..triangle[row_i].len() {
                triangle[row_i][col_i] = triangle[row_i][col_i]
                    + std::cmp::max(triangle[row_i + 1][col_i], triangle[row_i + 1][col_i + 1]);
            }
        }
        let max_path = triangle[0][0];

        max_path.to_string()
    }
    fn is_slow(&self) -> bool {
        false
    }
    fn problem_num(&self) -> i32 {
        67
    }
    fn answer_desc(&self) -> String {
        "Maximum".to_string()
    }
    fn real_answer(&self) -> crate::ProblemAnswer {
        crate::ProblemAnswer::Some("7273".to_string())
    }
}
