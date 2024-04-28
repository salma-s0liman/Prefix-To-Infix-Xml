/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project22;

/**
 *
 * @author Salma Soliman
 */
public class Stack {
     private int t, MAX_STACK_SIZE = 100;
        private String[] S;

        public Stack() {
            t = -1;
            S = new String[MAX_STACK_SIZE];
        }

        public boolean isEmpty() {
            return (t == -1);
        }

        public int size() {
            return (t + 1);
        }

        public void push(String el) {
            if (t == S.length - 1) {
                System.out.println("Stack is Full");
            } else {
                t = t + 1;
                S[t] = el;
            }
        }

        public String pop() {
            if (isEmpty()) {
                System.out.print("");
                return null;
            } else {
                t = t - 1;
                return S[t + 1];
            }
        }

        public String top() {
            if (isEmpty()) {
                System.out.println("Stack is Empty");
                return null;
            } else {
                return S[t];
            }
        }

        public void print() {
            System.out.print("[ ");
            for (int i = t; i >= 0; i--) {
                System.out.print(S[i] + " ");
            }
            System.out.println("]");
        }

    }
    



