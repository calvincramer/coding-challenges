use pathfinding::dijkstra;
use rayon::prelude::*;
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
        if x < board[y].len() - 1 {
            neighbors.push((board[y][x + 1], board[y][x + 1].0));
        }
        neighbors
    }
}

pub struct P082 {}
impl crate::Problem for P082 {
    #[allow(unused_variables)]
    fn solve(&self, verbose: bool) -> String {
        let lines = read_all_lines("src/input_files/p082_matrix.txt".to_string());

        let mut board = vec![];
        for (y, line) in lines.iter().enumerate() {
            let mut temp_row = vec![];
            for (x, n_str) in line.split(",").enumerate() {
                temp_row.push(Node(n_str.parse::<usize>().unwrap(), y, x));
            }
            board.push(temp_row);
        }

        let (min_path, min_path_sum) = (0..board.len())
            .into_par_iter()
            .map(|start_y| {
                let result = dijkstra(
                    &board[start_y][0],
                    |node| node.neighbors(&board),
                    |node| {
                        let &Node(n, y, x) = node;
                        x == board[board.len() - 1].len() - 1
                    },
                );
                let (path, total) = result.unwrap();
                // Add value of first node, since we only use cost on edges rather than nodes
                (path, total + board[start_y][0].0)
            })
            .min_by_key(|result| result.1)
            .unwrap();

        if verbose {
            println!("Path {:?}", min_path);
            println!("Total {:?}", min_path_sum);

            let mut path_positions = HashSet::new();
            for node in min_path {
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

        min_path_sum.to_string()
    }
    fn is_slow(&self) -> bool {
        false
    }
    fn problem_num(&self) -> i32 {
        82
    }
    fn answer_desc(&self) -> String {
        "Min path".to_string()
    }
    fn real_answer(&self) -> crate::ProblemAnswer {
        crate::ProblemAnswer::Some("260324".to_string())
    }
}
