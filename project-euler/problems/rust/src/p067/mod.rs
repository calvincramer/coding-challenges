use rust_math_tools::read_all_lines;

pub struct P067 {}
impl crate::Problem for P067 {
    #[allow(unused_variables)]
    fn run(&self, verbose: bool) -> (i32, String, String) {
        let lines = read_all_lines("src/p067/p067_triangle.txt".to_string());
        let mut triangle = Vec::<Vec<u32>>::new();
        for line in lines {
            triangle.push(line.split(" ").map(|s| s.parse::<u32>().unwrap()).collect())
        }

        // First pass, go up triangle (finds max path value)
        for row_i in (0..triangle.len()-1).rev() {
            for col_i in 0..triangle[row_i].len() {
                triangle[row_i][col_i] = triangle[row_i][col_i]
                    + std::cmp::max(triangle[row_i+1][col_i], triangle[row_i+1][col_i+1]);
            }
        }
        let max_path = triangle[0][0];

        (67, "Maximum".to_string(), max_path.to_string())
        // Answer: 7273
    }
}
