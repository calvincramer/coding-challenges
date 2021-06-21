#![allow(dead_code)]

/// Simulate player rolling dice and counting spaces landed at.
mod try1 {
    use rand;

    const DICE_FACES: usize = 4;
    const BOARD_LENGTH: usize = 40;

    fn roll_dice(pos: &mut usize, doubles_rolled: &mut usize) {
        let d1 = 1 + (rand::random::<usize>() % DICE_FACES);
        let d2 = 1 + (rand::random::<usize>() % DICE_FACES);

        match d1 == d2 {
            true => *doubles_rolled += 1,
            false => *doubles_rolled = 0,
        }
        match *doubles_rolled >= 3 {
            true => {
                *doubles_rolled = 0;
                *pos = 10;    // go to jail
            },
            false => *pos = (*pos + d1 + d2) % BOARD_LENGTH,
        }
    }

    fn on_chance(pos: usize) -> bool {
        pos == 7 || pos == 22 || pos == 36
    }

    fn on_community(pos: usize) -> bool {
        pos == 2 || pos == 17 || pos == 33
    }

    fn do_chance(pos: &mut usize) {
        match rand::random::<usize>() % 16 {
            0 => *pos = 0,      // GO
            1 => *pos = 10,     // JAIL
            2 => *pos = 11,     // C1
            3 => *pos = 24,     // E3
            4 => *pos = 39,     // H2
            5 => *pos = 5,      // R1
            6 | 7 => *pos = (((*pos + 5) / 10) + 5) % BOARD_LENGTH, // next railroad
            8 => *pos = if *pos < 12 || *pos >= 28 { 12 } else { 28 },  // next utility
            9 => *pos -= 3,     // go back 3
            _ => (),            // no movement
        }
    }

    fn do_community(pos: &mut usize) {
        match rand::random::<usize>() % 16 {
            0 => *pos = 0,  // GO
            1 => *pos = 10, // JAIL
            _ => (),        // no movement
        }
    }

    fn land_on(pos: &mut usize) {
        loop {
            if on_chance(*pos) {
                do_chance(pos);
                continue;
            }
            if on_community(*pos) {
                do_community(pos);
                continue
            }
            break;
        }
        // Go to jail?
        if *pos == 30 {
            *pos = 10;
        }
    }

    fn get_largest_i<T>(arr: &[T]) -> Option<usize>
        where T: Ord {
        if arr.len() == 0 {
            return None;
        }
        let mut larget_i = 0;
        for i in 1..arr.len() {
            if arr[i] > arr[larget_i] {
                larget_i = i;
            }
        }
        Some(larget_i)
    }

    fn try1(verbose: bool) -> String {
        const TOTAL: usize = 33_333_333;
        let mut board = [0u64; BOARD_LENGTH];
        let mut position = 0usize;
        let mut doubled_rolled = 0usize;

        for _ in 0..TOTAL {
            roll_dice(&mut position, &mut doubled_rolled);
            land_on(&mut position);
            board[position] += 1;
        }

        // Get three highest
        let largest_i_1 = get_largest_i(&board).unwrap();
        board[largest_i_1] = 0;
        let largest_i_2 = get_largest_i(&board).unwrap();
        board[largest_i_2] = 0;
        let largest_i_3 = get_largest_i(&board).unwrap();

        println!("{} {} {}", largest_i_1, largest_i_2, largest_i_3);

        "unknown".to_string()
    }
}

fn experiment1() -> String {
    const L: usize = 5;
    const ERROR: f64 = 0.000_01;
    let mut v = vec![1u64,0,0,0,0]; // All start at first position
    let mut new_v = vec![0u64,0,0,0,0];
    let mut prev_dist = vec![0.0, 0.0, 0.0, 0.0, 0.0];
    let mut iter = 0;
    println!("{:?} iter={}", v, iter);
    loop {
        // clear new_v
        for i in 0..L { new_v[i] = 0; }

        // apply roll distribution from each space, to get next iteration

        // 1. 50% roll 1, 50% roll 2
        // for i in 0..new_v.len() { new_v[i] = v[i] + v[((i + v.len() - 1) % v.len()) as usize ]; }

        // 2. 50% roll 1, 50% roll 2, except for space 0 which always moves 3
        for i in 0..L {
            if i == 0 {
                new_v[i+3] = v[i];
            } else {
                new_v[(i+1) % L] += v[i];
                new_v[(i+2) % L] += v[i];
            }
        }

        // Simultaneous update
        for i in 0..L { v[i] = new_v[i]; }

        // Confine large numbers to a certain precision
        if v[0] > 100_000_000 {
            for i in 0..L {
                v[i] = v[i] / 5;
            }
        }

        // Same distribution as previous iteration within error?
        let mut all_same = true;
        let mut new_dist = vec![0.0, 0.0, 0.0, 0.0, 0.0];
        // Calculate new dist
        let sum: f64 = v.iter().sum::<u64>() as f64;
        for i in 0..L { new_dist[i] = v[i] as f64 / sum; }
        for i in 0..L {
            if (new_dist[i] - prev_dist[i]).abs() > ERROR {
                all_same = false;
                break;
            }
        }
        // Update prev dist
        for i in 0..L { prev_dist[i] = new_dist[i]; }

        // Print current iteration
        println!("{:.3?} {:?} iter={} {}", new_dist, v, iter, if all_same { "SAME!!!" } else { "" });
        iter += 1;
        if iter > 1_000 || all_same { break; }
    }
    "experiment".to_string()
}

// Bad
mod try2 {
    const DICE_FACES: usize = 4;
    const L: usize = 40;

    fn apply_chance(at: usize, board: &mut Vec<f64>) {
        // 16 possibilities
        board[0] += 1f64/16f64;     // GO
        board[5] += 1f64/16f64;     // R1
        board[10] += 1f64/16f64;    // JAIL
        board[11] += 1f64/16f64;    // C1
        board[24] += 1f64/16f64;    // E3
        board[39] += 1f64/16f64;    // H2

        // next railroad (2x)
        board[5] += 1f64/32f64;
        board[15] += 1f64/32f64;
        board[25] += 1f64/32f64;
        board[35] += 1f64/32f64;

        // next utility
        board[12] += 2f64/48f64;    // two chances go to utility 1
        board[28] += 1f64/48f64;    // one chance goes to utility 2

        board[at-3] += 1f64/16f64;  // go back 3
        board[at] += 3f64/8f64;     // no movement 6 cards
    }

    fn apply_community(at: usize, board: &mut Vec<f64>) {
        board[0] += 1f64/16f64;    // GO
        board[10] += 1f64/16f64;    // JAIL
        board[at] += 7f64/8f64;     // no movement 14 cards
    }

    fn apply_normal(at: usize, board: &mut Vec<f64>) {
        // two 6-sided dice:
        board[(at+2) % L] += 1f64/36f64;
        board[(at+3) % L] += 2f64/36f64;
        board[(at+4) % L] += 3f64/36f64;
        board[(at+5) % L] += 4f64/36f64;
        board[(at+6) % L] += 5f64/36f64;
        board[(at+7) % L] += 6f64/36f64;
        board[(at+8) % L] += 5f64/36f64;
        board[(at+9) % L] += 4f64/36f64;
        board[(at+10) % L] += 3f64/36f64;
        board[(at+11) % L] += 2f64/36f64;
        board[(at+12) % L] += 1f64/36f64;
    }

    fn apply_distribution(at: usize, board: &mut Vec<f64>) {
        // Special places:
        // TODO Three doubles in a row - go to jail
        match at {
            2 | 17 | 33 => apply_community(at, board),
            7 | 22 | 36 => apply_chance(at, board),
            40 => board[10] += 1f64/40f64,  // go to jail space
            _ => apply_normal(at, board),
        };
    }

    pub fn try2(verbose: bool) -> String {
        const ERROR: f64 = 0.000_01;
        const MAX_BEFORE_SCALE_DOWN: f64 = 100_000.0;
        let mut v = vec![0f64; L];          // Board
        v[0] = 1.0;                         // Start at first position
        let mut prev_dist = vec![0f64; L];  // Distribution (likelihood to be on space)
        let mut iter = 0;
        println!("{:?} iter={}", v, iter);
        loop {
            // apply roll distribution from each space, to get next iteration
            for i in 0..L {
                apply_distribution(i, &mut v);
            }

            // Confine large numbers to a certain precision
            if v[0] > MAX_BEFORE_SCALE_DOWN {
                for i in 0..L {
                    v[i] = v[i] / 5.0;
                }
            }

            // Same distribution as previous iteration within error?
            let mut all_same = true;
            let mut new_dist = vec![0f64; L];
            // Calculate new dist
            let sum = v.iter().sum::<f64>();
            for i in 0..L { new_dist[i] = v[i] as f64 / sum; }
            for i in 0..L {
                if (new_dist[i] - prev_dist[i]).abs() > ERROR {
                    all_same = false;
                    break;
                }
            }
            // Update prev dist
            for i in 0..L { prev_dist[i] = new_dist[i]; }

            // Print current iteration
            println!("{:.3?} {:?} iter={} {}", new_dist, v, iter, if all_same { "SAME!!!" } else { "" });
            iter += 1;
            if iter > 1_000 || all_same { break; }
        }
        "experiment".to_string()
    }
}

// Try 3
// Make 2D transition matrix from each place to the next
// Raise matrix to Nth power to simulate N moves
// Credit: Tom Nguyen
// TODO: apply real distribution for each space. Need to account for how normal moves may end on a chance or community chest, which may jump them other locations
// TODO: also be careful since one chance space can move back three spaces onto a community chest (recursive)
mod try3 {
    use ndarray::{Array, Dimension, arr2, Ix2};

    const L: usize = 40;

    fn apply_chance(at: usize, t: &mut Array<f64, Ix2>) {
        // 16 possibilities
        t[[at, 0]]  += 1f64/16f64;     // GO
        t[[at, 5]]  += 1f64/16f64;     // R1
        t[[at, 10]] += 1f64/16f64;    // JAIL
        t[[at, 11]] += 1f64/16f64;    // C1
        t[[at, 24]] += 1f64/16f64;    // E3
        t[[at, 39]] += 1f64/16f64;    // H2

        // next railroad (2x)
        t[[at, 5]]  += 1f64/32f64;
        t[[at, 15]] += 1f64/32f64;
        t[[at, 25]] += 1f64/32f64;
        t[[at, 35]] += 1f64/32f64;

        // next utility
        t[[at, 12]] += 2f64/48f64;    // two chances go to utility 1
        t[[at, 28]] += 1f64/48f64;    // one chance goes to utility 2

        t[[at, at-3]] += 1f64/16f64;  // go back 3
        t[[at, at]]   += 3f64/8f64;     // no movement 6 cards
    }

    fn apply_community(at: usize, t: &mut Array<f64, Ix2>) {
        t[[at, 0] ] += 1f64/16f64;    // GO
        t[[at, 10]] += 1f64/16f64;    // JAIL
        t[[at, at]] += 7f64/8f64;     // no movement 14 cards
    }

    fn apply_normal(at: usize, t: &mut Array<f64, Ix2>) {
        // two 6-sided dice:
        t[[at, (at+2)  % L]] += 1f64/36f64;
        t[[at, (at+3)  % L]] += 2f64/36f64;
        t[[at, (at+4)  % L]] += 3f64/36f64;
        t[[at, (at+5)  % L]] += 4f64/36f64;
        t[[at, (at+6)  % L]] += 5f64/36f64;
        t[[at, (at+7)  % L]] += 6f64/36f64;
        t[[at, (at+8)  % L]] += 5f64/36f64;
        t[[at, (at+9)  % L]] += 4f64/36f64;
        t[[at, (at+10) % L]] += 3f64/36f64;
        t[[at, (at+11) % L]] += 2f64/36f64;
        t[[at, (at+12) % L]] += 1f64/36f64;
    }

    fn apply_distribution(at: usize, t: &mut Array<f64, Ix2>) {
        // Special places:
        // TODO Three doubles in a row - go to jail
        match at {
            2 | 17 | 33 => apply_community(at, t),
            7 | 22 | 36 => apply_chance(at, t),
            30 => t[[30, 10]] += 1f64,  // go to jail space
            _ => apply_normal(at, t),
        };
    }

    fn print_t (array: &Array<f64, Ix2>) {
        for r in 0..L {
            println!();
            for c in 0..L {
                let n = array[[r, c]];
                if n == 0.0f64 {
                    print!("0     ");
                } else {
                    print!("{:.3} ", n);
                }
            }
        }
        println!();
    }

    fn get_largest_i<T>(arr: &[T]) -> Option<usize>
        where T: PartialOrd {
        if arr.len() == 0 {
            return None;
        }
        let mut larget_i = 0;
        for i in 1..arr.len() {
            if arr[i] > arr[larget_i] {
                larget_i = i;
            }
        }
        Some(larget_i)
    }

    pub fn try3(verbose: bool) -> String {
        let mut transition = Array::<f64, _ >::zeros((L, L));
        for i in 0..L {
            apply_distribution(i, &mut transition);
        }
        let orig_t = transition.clone();
        // Raise transition to nth power
        for _ in 0..100 {
            transition = transition.dot(&orig_t);
        }
        if verbose {
            print_t(&transition);
        }

        // Get three highest
        let mut row = [0f64; L];
        for i in 0..L { row[i] = transition[[0, i]]; }
        let largest_i_1 = get_largest_i(&row).unwrap();
        row[largest_i_1] = 0.0;
        let largest_i_2 = get_largest_i(&row).unwrap();
        row[largest_i_2] = 0.0;
        let largest_i_3 = get_largest_i(&row).unwrap();

        println!("{} {} {}", largest_i_1, largest_i_2, largest_i_3);

        "experiment".to_string()
    }
}


pub struct P084 {}
impl crate::Problem for P084 {
    #[allow(unused_variables)]
    fn solve(&self, verbose: bool) -> String {
        // try1::try1(verbose)
        // experiment1()
        // try2::try2(verbose)
        try3::try3(verbose)
    }
    fn is_slow(&self) -> bool { false }
    fn problem_num(&self) -> i32 { 84 }
    fn answer_desc(&self) -> String { "Modal string".to_string() }
    fn real_answer(&self) -> crate::ProblemAnswer { crate::ProblemAnswer::Some("101524".to_string()) }
}
