use std::fmt;
use rust_math_tools::read_all_lines;
use std::cmp::Ordering;

#[derive(Debug, PartialEq, Eq, PartialOrd, Ord, Copy, Clone)]
enum Suite {
    S = 0, C = 1, D = 2, H = 3,
}

#[derive(Copy, Clone)]
struct Card {
    number: u8,
    suite: Suite,
}

#[derive(Debug, PartialEq, Eq, PartialOrd, Ord)]
enum Rank {
    CardHigh = 0,
    Pair = 1,
    TwoPair = 2,
    Triple = 3,
    Straight = 4,
    Flush = 5,
    FullHouse = 6,
    Quadruple = 7,
    StraightFlush = 8,
    RoyalFlush = 9,
}

#[derive(Debug)]
struct Hand {
    cards: Vec<Card>,
    rank: Rank,
    rank_of: Card,
}

impl fmt::Debug for Card {
    fn fmt(&self, f: &mut fmt::Formatter) -> fmt::Result {
        let map_n = |n: u8| -> char {
            match n {
                10 => 'T',
                11 => 'J',
                12 => 'Q',
                13 => 'K',
                14 => 'A',
                _ => std::char::from_digit(n as u32, 10).unwrap(),
            }
        };
        write!(f, "{}{:?}", map_n(self.number), self.suite)
    }
}

impl Hand {
    fn new(mut cards: Vec<Card>) -> Hand {
        cards.sort_by(compare_cards);
        let (r, r_of) = rank_hand(&cards);
        Hand { cards, rank: r, rank_of: r_of }
    }

    fn compare_hands(hand1: &Hand, hand2: &Hand) -> Ordering {
        use Ordering::*;
        match hand1.rank.cmp(&hand2.rank) {
            Less => Less,
            Greater => Greater,
            Equal => {
                match hand1.rank_of.number.cmp(&hand2.rank_of.number) {
                    Less => Less,
                    Greater => Greater,
                    Equal => {
                        // Highest card wins (by number, not by suite)
                        for i in (0..hand1.cards.len()).rev() {
                            if hand1.cards[i].number != hand1.cards[i].number {
                                return hand1.cards[i].number.cmp(&hand1.cards[i].number);
                            }
                        }
                        Equal
                    }
                }
            },
        }
    }
}

fn rank_hand(hand: &Vec<Card>) -> (Rank, Card) {
    use Rank::*;
    let all_same_suite: bool = {
        let mut temp = true;
        for i in 1..hand.len() {
            if hand[0].suite != hand[i].suite {
                temp = false;
                break;
            }
        }
        temp
    };

    let is_straight: bool = {
        let mut temp = true;
        for i in 1..hand.len() {
            if hand[i-1].number + 1 != hand[i].number {
                temp = false;
                break;
            }
        }
        temp
    };

    if is_straight && all_same_suite {
        return match hand[0].number == 10 {
            true => (RoyalFlush, hand[0].clone()),
            false => (StraightFlush, hand[0].clone()),
        };
    }

    // Only one quad allowed
    for i in 0..hand.len()-3 {
        if hand[i].number == hand[i+1].number && hand[i].number == hand[i+2].number && hand[i].number == hand[i+3].number {
            return (Quadruple, hand[i].clone());
        }
    }

    // Find triples
    let mut has_triple = false;
    let mut triple_i = 0;
    for i in 0..hand.len()-2 {
        if hand[i].number == hand[i + 1].number && hand[i].number == hand[i + 2].number {
            has_triple = true;
            triple_i = i;
            break;  // Only one triple
        }
    }

    // Find pairs
    let mut num_pairs = 0;
    let mut last_pair_i = 0;
    for i in 0..hand.len()-1 {
        if hand[i].number == hand[i + 1].number {
            if has_triple && (triple_i == i || triple_i+1 == i) {
                continue;
            }
            num_pairs += 1;
            last_pair_i = i;
        }
    }

    // Return:
    if has_triple && num_pairs == 1 {
        (FullHouse, hand[triple_i].clone())
    } else if all_same_suite {
        (Flush, hand[hand.len()-1].clone())
    } else if is_straight {
        (Straight, hand[hand.len()-1].clone())
    } else if has_triple {
        (Triple, hand[triple_i].clone())
    } else if num_pairs == 2 {
        (TwoPair, hand[last_pair_i].clone())
    } else if num_pairs == 1 {
        (Pair, hand[last_pair_i].clone())
    } else {
        (CardHigh, hand[hand.len()-1].clone())
    }
}

fn parse_card(hand: &str) -> Card {
    use Suite::*;
    let hand: Vec<char> = hand.chars().collect();
    Card {
        number: {
            match hand[0] {
                'T' => 10,
                'J' => 11,
                'Q' => 12,
                'K' => 13,
                'A' => 14,
                _ => hand[0].to_digit(10).unwrap() as u8,
            }
        },
        suite: {
            match hand[1] {
                'S' => S,
                'C' => C,
                'D' => D,
                'H' => H,
                _ => panic!("Suit not recognized: {}", hand[1])
            }
        }
    }
}

fn compare_cards(c1: &Card, c2: &Card) -> Ordering {
    use Ordering::*;
    match c1.number.cmp(&c2.number)   {
        Less => Less,
        Greater => Greater,
        Equal => c1.suite.cmp(&c2.suite),
    }
}

fn parse_hands() -> Vec<(Hand, Hand)> {
    let hands_data = read_all_lines("src/input_files/p054_poker.txt".to_string());
    let mut hands = vec![];
    for s in hands_data {
        let mut p1 = vec![];
        let mut p2 = vec![];
        for (i, card_str) in s.split(" ").enumerate() {
            let card = parse_card(card_str);
            (if i < 5 { &mut p1 } else { &mut p2 }).push(card);
        }
        hands.push( (Hand::new(p1), Hand::new(p2)) );
    }
    hands
}

pub struct P054 {}
impl crate::Problem for P054 {
    #[allow(unused_variables)]
    fn solve(&self, verbose: bool) -> String {
        let t = parse_hands()
            .iter()
            .filter(|(p1, p2)| Hand::compare_hands(&p1, &p2) == Ordering::Greater)
            .count();

        t.to_string()
    }
    fn is_slow(&self) -> bool { false }
    fn problem_num(&self) -> i32 { 54 }
    fn answer_desc(&self) -> String { "Player 1 # wins".to_string() }
    fn real_answer(&self) -> crate::ProblemAnswer { crate::ProblemAnswer::Some("376".to_string()) }
}
