pub mod p028 {
    pub fn run() {
        println!("Problem 28");
        let n = 500;

        // Individual diagonals:
        let mut sum = 1u64;    // Include the middle 1
        // Top right diagonal
        sum += (1u64..=500).map(|n| (2 * n + 1).pow(2)).sum::<u64>();
        // Top left diagonal
        sum += (1u64..=500).map(|n| 4 * n * n + 2 * n + 1).sum::<u64>();
        // Bottom left diagonal
        sum += (1u64..=500).map(|n| 4 * n * n + 1).sum::<u64>();
        // Bottom right diagonal
        sum += (1u64..=500).map(|n| 4 * n * n - 2 * n + 1).sum::<u64>();
        println!("{}", sum);

        // Add all diagonals together:
        println!("{}", 1 + 4 * (1u64..=n).map(|n| 4 * n * n + n + 1).sum::<u64>());

        // Integrate the sum:
        let spiral_diag_sum = |r: u64| {
            (16 * r * r * r + 30 * r * r + 26 * r + 3) / 3
        };
        println!("{}", spiral_diag_sum(n));
    }
    // Answer: 669171001
}
