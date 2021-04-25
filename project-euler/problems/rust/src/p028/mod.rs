const N: u64 = 500;

#[allow(dead_code)]
fn try1() {
    // Individual diagonals:
    let mut sum = 1u64;    // Include the middle 1
    // Top right diagonal
    sum += (1u64..=N).map(|n| (2 * n + 1).pow(2)).sum::<u64>();
    // Top left diagonal
    sum += (1u64..=N).map(|n| 4 * n * n + 2 * n + 1).sum::<u64>();
    // Bottom left diagonal
    sum += (1u64..=N).map(|n| 4 * n * n + 1).sum::<u64>();
    // Bottom right diagonal
    sum += (1u64..=N).map(|n| 4 * n * n - 2 * n + 1).sum::<u64>();
    println!("{}", sum);
}

#[allow(dead_code)]
fn try2() {
    // Add all diagonals together:
    println!("{}", 1 + 4 * (1u64..=N).map(|n| 4 * n * n + n + 1).sum::<u64>());
}

pub struct P028 {}
impl crate::Problem for P028 {
    #[allow(unused_variables)]
    fn run(&self, verbose: bool) -> (i32, String, String) {
        // Integrate the sum:
        let spiral_diag_sum = |r: u64| {
            (16 * r * r * r + 30 * r * r + 26 * r + 3) / 3
        };
        (28, "Sum".to_string(), spiral_diag_sum(N).to_string())
        // Answer: 669171001
    }
}
