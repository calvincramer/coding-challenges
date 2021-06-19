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

// Experiment 2
// Make 2D transition matrix from each place to the next
// Raise matrix to Nth power to simulate N moves
// Credit: Tom Nguyen 


pub struct P084 {}
impl crate::Problem for P084 {
    #[allow(unused_variables)]
    fn solve(&self, verbose: bool) -> String {
//        try1(verbose)
        experiment1()
    }
    fn is_slow(&self) -> bool { false }
    fn problem_num(&self) -> i32 { 84 }
    fn answer_desc(&self) -> String { "Modal string".to_string() }
    fn real_answer(&self) -> crate::ProblemAnswer { crate::ProblemAnswer::Some("???".to_string()) }
}
