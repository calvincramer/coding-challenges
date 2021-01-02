//[COMPLETED]

// Definition for singly-linked list.
 #[derive(PartialEq, Eq, Clone, Debug)]
 pub struct ListNode {
   pub val: i32,
   pub next: Option<Box<ListNode>>
 }

 impl ListNode {
    #[inline]
    fn new(val: i32) -> Self {
        ListNode {
            next: None,
            val
        }
    }

     fn print_list (self) {
         print!("{}", self.val);
         match self.next {
             Some(n) => {
                 print!(" -> ");
                 (*n).print_list()
             },
             None => println!(),
         }
     }
 }

struct Solution;

impl Solution {
    pub fn add_two_numbers(l1: Option<Box<ListNode>>, l2: Option<Box<ListNode>>) -> Option<Box<ListNode>> {
        Solution::add(l1, l2, 0)
    }

    fn add(l1: Option<Box<ListNode>>, l2: Option<Box<ListNode>>, carry: i32) -> Option<Box<ListNode>> {
        // None case
        if l1.is_none() && l2.is_none() {
            match carry {
                c if c > 9 => return Some(Box::new(ListNode {
                                                        val: c % 10,
                                                        next: Solution::add(None, None, c / 10) })),
                0 => return None,
                _ => return Some(Box::new(ListNode { val: carry, next: None })),
            };
        }

        let l1_next: Option<Box<ListNode>>;
        let l1_val: i32;
        let l2_next: Option<Box<ListNode>>;
        let l2_val: i32;

        // Unwrap if exist
        match l1 {
            Some(x) => {
                l1_next = (*x).next;
                l1_val = (*x).val;
            },
            None => {
                l1_next = None;
                l1_val = 0;
            }
        }
        match l2 {
            Some(x) => {
                l2_next = (*x).next;
                l2_val = (*x).val;
            },
            None => {
                l2_next = None;
                l2_val = 0;
            }
        }

        let sum: i32 = l1_val + l2_val + carry;
        let digit_here: i32 = sum % 10;
        let carry_next: i32 = sum / 10;

        Some(Box::new(ListNode {
            val: digit_here,
            next: Solution::add(l1_next, l2_next, carry_next),
        }))
    }
}

fn main() {
    // Test case 0
//    let temp0: Option<Box<ListNode>> = Some(Box::new(ListNode::new(3)));
//    let temp1: Option<Box<ListNode>> = Some(Box::new(ListNode { val: 4, next: temp0 } ));
//    let list0: Option<Box<ListNode>> = Some(Box::new(ListNode { val: 2, next: temp1 } ));
//
//    let temp2: Option<Box<ListNode>> = Some(Box::new(ListNode::new(4)));
//    let temp3: Option<Box<ListNode>> = Some(Box::new(ListNode { val: 6, next: temp2 } ));
//    let list1: Option<Box<ListNode>> = Some(Box::new(ListNode { val: 5, next: temp3 } ));

    // Test case 1
//    let temp0: Option<Box<ListNode>> = Some(Box::new(ListNode::new(9)));
//    let list0: Option<Box<ListNode>> = Some(Box::new(ListNode { val: 9, next: temp0 } ));
//    let list1: Option<Box<ListNode>> = Some(Box::new(ListNode::new(9)));

    // Test case 2
    let temp0: Option<Box<ListNode>> = Some(Box::new(ListNode::new(9)));
    let temp1: Option<Box<ListNode>> = Some(Box::new(ListNode { val: 9, next: temp0 } ));
    let list0: Option<Box<ListNode>> = Some(Box::new(ListNode { val: 8, next: temp1 } ));

    let list1: Option<Box<ListNode>> = Some(Box::new(ListNode::new(2)));

    let ret = Solution::add_two_numbers(list0, list1);
    println!("ret = {:?}", ret);
    if ret.is_some() {
        (*ret.unwrap()).print_list();
    }
}











/* Try 1: */
//impl Solution {
//    pub fn add_two_numbers(l1: Option<Box<ListNode>>, l2: Option<Box<ListNode>>) -> Option<Box<ListNode>> {
//        if l1.is_none() && l2.is_none() { return None; }
//        if l1.is_none() || l2.is_none() {
//            // Check if we need to propagate a value >= 10
//            let a;  // Non-none list
//            if l1.is_none() {
//                a = l2;
//            } else {
//                a = l1;
//            }
//            let a = *a.unwrap();
//            if a.val > 9 {
//                let carry = Some(Box::new(ListNode {val: a.val / 10, next: None} ));
//                return Solution::add_two_numbers( Some(Box::new( ListNode {val: a.val % 10, next: carry} )), None);
//            }
//            else {
//                return Some(Box::new(a));   // Rebox :(
//            }
//        }
//
//        let ln1: ListNode = *l1.unwrap();
//        let ln2: ListNode = *l2.unwrap();
//
//        let cur_val: i32 = (ln1.val + ln2.val) % 10;
//        let carry: i32 = (ln1.val + ln2.val) / 10;
//
//        let next_ln1: Option<Box<ListNode>> = match carry {
//            0 => ln1.next,
//            _ => { match ln1.next {
//                Some(ln1_next_box) => {
//                    let ln1_next: ListNode = *ln1_next_box;
//                    Some(Box::new(ListNode {val: ln1_next.val + carry, next: ln1_next.next} ))
//                },
//                None => Some(Box::new(ListNode::new(carry))),
//            }
//            },
//        };
//
//        return Some(Box::new(ListNode {
//            val: cur_val,
//            next: Solution::add_two_numbers(next_ln1, ln2.next),
//        }));
//    }