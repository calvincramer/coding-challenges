// Assuming f is increasing
fn map_fill_out_arr(arr: &mut [bool], f: fn(u64) -> u64) {
    let mut n = 0;
    loop {
        let ans = f(n);
        n += 1;
        // if ans < 0 { continue; }
        if ans >= arr.len() as u64 {
            break;
        }
        arr[ans as usize] = true;
    }
}

#[derive(Debug)]
struct Polygonals {
    triangle: [bool; 10_000],
    square: [bool; 10_000],
    pentagonal: [bool; 10_000],
    hexagonal: [bool; 10_000],
    heptagonal: [bool; 10_000],
    octagonal: [bool; 10_000],
}

impl Polygonals {
    #[rustfmt::skip]
    fn new() -> Polygonals {
        let mut temp = Polygonals {
            triangle:   [false; 10_000],
            square:     [false; 10_000],
            pentagonal: [false; 10_000],
            hexagonal:  [false; 10_000],
            heptagonal: [false; 10_000],
            octagonal:  [false; 10_000],
        };
        map_fill_out_arr(&mut temp.triangle,   |n: u64| -> u64 { (n*n + n) / 2 }    );
        map_fill_out_arr(&mut temp.square,     |n: u64| -> u64 { n*n }              );
        map_fill_out_arr(&mut temp.pentagonal, |n: u64| -> u64 { (3*n*n - n) / 2 }  );
        map_fill_out_arr(&mut temp.hexagonal,  |n: u64| -> u64 { 2*n*n - n }        );
        map_fill_out_arr(&mut temp.heptagonal, |n: u64| -> u64 { (5*n*n - 3*n) / 2 });
        map_fill_out_arr(&mut temp.octagonal,  |n: u64| -> u64 { 3*n*n - 2*n }      );
        temp
    }

    fn get_index(&self, i: usize) -> &[bool] {
        match i {
            0 => &self.triangle,
            1 => &self.square,
            2 => &self.pentagonal,
            3 => &self.hexagonal,
            4 => &self.heptagonal,
            5 => &self.octagonal,
            _ => panic!(),
        }
    }
}

fn in_array(target: u64, arr: &[u64]) -> bool {
    for i in 0..arr.len() {
        if arr[i] == target && arr[i] != 0 {
            return true;
        }
    }
    false
}

fn find_cycle(
    count: usize,
    nums_found: &mut [u64; 6], // triangle, square, ... octagonal number found
    prev_2_digits: u64,
    polygonals: &Polygonals,
) -> bool {
    if count == 0 {
        // Start: find any triangle number and recurse from there
        for n in 1_000u64..=9_999 {
            if polygonals.triangle[n as usize] {
                nums_found[0] = n;
                if find_cycle(count + 1, nums_found, n % 100, polygonals) {
                    return true;
                }
            }
        }
    } else if count < 5 {
        // Find any polygonal number based on previous
        if prev_2_digits < 10 {
            return false; // Don't allow 0 as a leading digit or else won't have 4-digit number
        }
        for bottom in 10..=99 {
            let n = prev_2_digits * 100 + bottom;
            if in_array(n, nums_found) {
                continue;
            }
            // See if n is any of the polygonal numbers (square through octagonal)
            for j in 1..=5 {
                if nums_found[j] != 0 {
                    continue;
                }
                if polygonals.get_index(j)[n as usize] {
                    nums_found[j] = n;
                    if find_cycle(count + 1, nums_found, n % 100, polygonals) {
                        return true;
                    }
                    nums_found[j] = 0; // Clear previous non-passing path
                }
            }
        }
    } else {
        // Base case: find last number
        let n = prev_2_digits * 100 + (nums_found[0] / 100);
        if in_array(n, nums_found) {
            return false;
        }
        for j in 1..=5 {
            if nums_found[j] != 0 {
                continue;
            }
            if !polygonals.get_index(j)[n as usize] {
                return false;
            }
            // Got answer:
            nums_found[j] = n;
            return true;
        }
    }
    false
}

pub struct P061 {}
impl crate::Problem for P061 {
    fn solve(&self, verbose: bool) -> String {
        let p = Polygonals::new();
        let mut nums = [0u64; 6];
        let found = find_cycle(0, &mut nums, 0, &p);
        if found {
            if verbose {
                println!("nums: {:?}", nums);
                println!("nums[0] triangle?: {}", p.triangle[nums[0] as usize]);
                println!("nums[1] square?: {}", p.square[nums[1] as usize]);
                println!("nums[2] pentagonal?: {}", p.pentagonal[nums[2] as usize]);
                println!("nums[3] hexagonal?: {}", p.hexagonal[nums[3] as usize]);
                println!("nums[4] heptagonal?: {}", p.heptagonal[nums[4] as usize]);
                println!("nums[5] octagonal?: {}", p.octagonal[nums[5] as usize]);
            }
            return nums.iter().sum::<u64>().to_string();
        }
        "Not found".to_string()
    }
    fn is_slow(&self) -> bool {
        false
    }
    fn problem_num(&self) -> i32 {
        61
    }
    fn answer_desc(&self) -> String {
        "Sum".to_string()
    }
    fn real_answer(&self) -> crate::ProblemAnswer {
        crate::ProblemAnswer::Some("28684".to_string())
    }
}
