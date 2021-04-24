pub mod p018 {
    // Returns maximum path sum, left right bool path vector
    fn traverse(tri: &Vec<Vec<u64>>, y: usize, x: usize) -> (u64, Vec<bool>) {
        if y >= tri.len() { return (0, vec![]); }
        if x >= tri[y].len() { return (0, vec![]); }

        let (max_l, mut path_l) = traverse(tri, y + 1, x);
        let (max_r, mut path_r) = traverse(tri, y + 1, x + 1);

        match max_l > max_r {
            true => {
                path_l.push(false);
                (tri[y][x] + max_l, path_l)
            },
            false => {
                path_r.push(true);
                return (tri[y][x] + max_r, path_r)
            },
        }
    }


    pub fn run() {
        println!("Problem 18");
        let mut triangle: Vec<Vec<u64>> = vec![];
        triangle.push(vec![75]);
        triangle.push(vec![95, 64]);
        triangle.push(vec![17, 47, 82]);
        triangle.push(vec![18, 35, 87, 10]);
        triangle.push(vec![20, 04, 82, 47, 65]);
        triangle.push(vec![19, 01, 23, 75, 03, 34]);
        triangle.push(vec![88, 02, 77, 73, 07, 63, 67]);
        triangle.push(vec![99, 65, 04, 28, 06, 16, 70, 92]);
        triangle.push(vec![41, 41, 26, 56, 83, 40, 80, 70, 33]);
        triangle.push(vec![41, 48, 72, 33, 47, 32, 37, 16, 94, 29]);
        triangle.push(vec![53, 71, 44, 65, 25, 43, 91, 52, 97, 51, 14]);
        triangle.push(vec![70, 11, 33, 28, 77, 73, 17, 78, 39, 68, 17, 57]);
        triangle.push(vec![91, 71, 52, 38, 17, 14, 91, 43, 58, 50, 27, 29, 48]);
        triangle.push(vec![63, 66, 04, 68, 89, 53, 67, 30, 73, 16, 69, 87, 40, 31]);
        triangle.push(vec![04, 62, 98, 27, 23, 09, 70, 98, 73, 93, 38, 53, 60, 04, 23]);

        let (max, mut max_path) = traverse(&triangle, 0, 0);
        println!("{} -> {:?}", max, max_path);
        let mut max_i = 0;  // Track the max path
        let mut add_to_start = triangle.len() * 3 - 3;

        for row in triangle {
            print!("{:x$}", "", x = add_to_start);
            if add_to_start >= 3 { add_to_start -= 3; }
            for i in 0..row.len() {
                if i == max_i {
                    print!("|{:2}|  ", row[i]);
                } else {
                    print!(" {:2}   ", row[i]);
                }
            }
            println!();
            max_i += max_path.pop().unwrap() as usize;
        }
    }
    // Answer: 1074
}