use num_integer::sqrt;

// TODO too slow

pub struct P075 {}
impl crate::Problem for P075 {
    #[allow(unused_variables)]
    fn run(&self, verbose: bool) -> (i32, String, String) {
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

        (75, "answer".to_string(), total.to_string())
        // Answer: ???
    }
}
