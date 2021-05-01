pub struct P045 {}
impl crate::Problem for P045 {
    fn run(&self, verbose: bool) -> (i32, String, String) {
        let mut n_t = 1;    // triangle number index (the n of T_n)
        let mut n_p = 1;    // pentagonal number index
        let mut n_h = 1;    // hexagonal number index
        let mut tri_num = 1;
        let mut pen_num = 1;
        let mut hex_num = 1;
        loop {
            if tri_num == pen_num && tri_num == hex_num {
                if verbose { println!("{} -> T_{} P_{} H_{}", tri_num, n_t, n_p, n_h); }
                if n_t > 285 { return (45, "Tri Pen Hex".to_string(), tri_num.to_string()) }
            }
            // Increment the smallest:
            if tri_num < pen_num && tri_num < hex_num {
                tri_num += n_t + 1;     // These increments were found by solving for f(n+1) - f(n)
                n_t += 1;
            } else if pen_num < hex_num {
                pen_num += (3*n_p) + 1;
                n_p += 1;
            } else {
                hex_num += (4*n_h) + 1;
                n_h += 1;
            }
        }
        // Answer: 1533776805
    }
}
