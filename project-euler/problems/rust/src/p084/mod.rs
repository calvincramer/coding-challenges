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

pub struct P084 {}
impl crate::Problem for P084 {
    #[allow(unused_variables)]
    fn solve(&self, verbose: bool) -> String {
        try1(verbose)
    }
    fn is_slow(&self) -> bool { false }
    fn problem_num(&self) -> i32 { 84 }
    fn answer_desc(&self) -> String { "Modal string".to_string() }
    fn real_answer(&self) -> crate::ProblemAnswer { crate::ProblemAnswer::Some("???".to_string()) }
}
