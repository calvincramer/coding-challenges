fn main() {
    // // Individual diagonals:
    // let mut sum = 1u64;    // Include the middle 1
    // // Top right diagonal
    // sum += (1u64..=500).map(|n| (2*n + 1).pow(2)).sum::<u64>();
    // // Top left diagonal
    // sum += (1u64..=500).map(|n| 4*n*n + 2*n + 1).sum::<u64>();
    // // Bottom left diagonal
    // sum += (1u64..=500).map(|n| 4*n*n + 1).sum::<u64>();
    // // Bottom right diagonal
    // sum += (1u64..=500).map(|n| 4*n*n - 2*n + 1).sum::<u64>();

    // Add all diagonals together:
    let sum = 1 + 4*(1u64..=500).map(|n| 4*n*n + n + 1).sum::<u64>();

    println!("{}", sum)
}
// Answer: 669171001
