use pathfinding::dijkstra;
use rust_math_tools::read_all_lines;
use std::collections::HashSet;

#[derive(Debug, Copy, Clone, Eq, PartialEq, Hash)]
struct Node(usize, usize, usize); // number, y, x
impl Node {
    fn neighbors(&self, board: &Vec<Vec<Node>>) -> Vec<(Node, usize)> {
        let &Node(_, y, x) = self;
        let mut neighbors = vec![];
        if y > 0 {
            neighbors.push((board[y - 1][x], board[y - 1][x].0));
        }
        if y < board.len() - 1 {
            neighbors.push((board[y + 1][x], board[y + 1][x].0));
        }
        if x > 0 {
            neighbors.push((board[y][x - 1], board[y][x - 1].0));
        }
        if x < board[y].len() - 1 {
            neighbors.push((board[y][x + 1], board[y][x + 1].0));
        }
        neighbors
    }
}

pub struct P083 {}
impl crate::Problem for P083 {
    #[allow(unused_variables)]
    fn solve(&self, verbose: bool) -> String {
        let lines = read_all_lines("src/input_files/p083_matrix.txt".to_string());

        let mut board = vec![];
        for (y, line) in lines.iter().enumerate() {
            let mut temp_row = vec![];
            for (x, n_str) in line.split(",").enumerate() {
                temp_row.push(Node(n_str.parse::<usize>().unwrap(), y, x));
            }
            board.push(temp_row);
        }

        let result = dijkstra(
            &board[0][0],
            |node| node.neighbors(&board),
            |node| {
                let &Node(n, y, x) = node;
                y == board.len() - 1 && x == board[board.len() - 1].len() - 1
            },
        );
        let (path, mut total) = result.unwrap();
        // Add value of first node, since we only use cost on edges rather than nodes
        total += board[0][0].0;

        if verbose {
            println!("Path {:?}", path);
            println!("Total {:?}", total);

            let mut path_positions = HashSet::new();
            for node in path {
                path_positions.insert((node.1, node.2));
            }
            for y in 0..board.len() {
                for x in 0..board[y].len() {
                    print!(
                        "{}",
                        match path_positions.contains(&(y, x)) {
                            true => "X",
                            false => "·",
                        }
                    );
                }
                println!();
            }
        }

        total.to_string()
    }
    fn is_slow(&self) -> bool {
        false
    }
    fn problem_num(&self) -> i32 {
        83
    }
    fn answer_desc(&self) -> String {
        "Min path".to_string()
    }
    fn real_answer(&self) -> crate::ProblemAnswer {
        crate::ProblemAnswer::Some("425185".to_string())
    }
}
