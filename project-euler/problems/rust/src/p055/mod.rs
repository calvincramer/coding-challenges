use rust_math_tools::is_lychrel;

pub struct P055 {}
impl crate::Problem for P055 {
    #[allow(unused_variables)]
    fn run(&self, verbose: bool) -> (i32, String, String) {
        let total = (0u64..10_000).map(is_lychrel).filter(|b| *b).count();
        (55, "Num Lychrel nums".to_string(), total.to_string())
        // 249
    }
}
