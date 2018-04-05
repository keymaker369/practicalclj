(ns practicalclj)

(ns my-namespace
  (:require [dodatni :as dod]))

(dod/hello-world)


(println "hello world")

(def zika "ZIKA!")
;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn rangechecker
  "Returns a function that determines if a number is in a provided range."
  [min max]
  (fn [num]
    (and (<= num max)
         (<= min num))))

(def izmedju5i9 (rangechecker 5 9))
(izmedju5i9 8)
;;;;;;;;;;;;;;;;;;;;
(def times-pi (partial * 3.14159))
(times-pi 3)

(defn saberi [a b c] (+ a b c))
(def saberi-dva (partial saberi 10))
;;;;;;;;;;;;;
(def my-fn (comp - *))
(my-fn 3 3 3)

(def my-fn (comp (partial * 10) - *))
(my-fn 5 3)
;;;;;;;;;;;;;;;;;
;;Common Numeric Functions
(inc 5)
(dec 5)
(quot 5 2)
(rem 5 2)
(min 5 10 2)
(max 5 10 2)
(== 5 5.0)
(< 5 10)
(< 5 10 9)
(< 5 7 9)
(<= 5 5 10)
(zero? 0.0)
(pos? 5)
(pos? -5)
(neg? -5)
(number? 5)
(number? "hello")
;;Common String Functions
"Most programmers write a \"Hello World\" program when they learn a new language"
(str "I have " 5 " books.")
(subs "Hello World" 6)
(subs "Hello World" 0 5)
(string? "test")
;;Regular Expression Functions
(re-pattern " [a-zA-Z]*")
(re-matches #"[a-zA-Z]* " "test")
(def my-matcher (re-matcher #" [a-zA-Z]* " "test"))
(re-find my-matcher)
(re-seq #" [a-z] " "test")
;;Common Boolean Functions
(not (== 5 5))
(and (== 5 5) (< 1 2))
(or (== 5 5) (== 5 4))
;;Characters
(char 97)
;;Keywords
(keyword "hello")
(keyword "foo" "bar")
(keyword? :hello)
;;Lists
(def nums '(1 2 3 4 5))
nums
(list 1 2 3)
(peek '(1 2 3))

(def moja-lista (list 9 7 8 4))
(peek moja-lista)
(pop moja-lista)
(list? moja-lista)

;;Vectors
(def nums [1 2 3 4 5])
(nums 3)
(vector 1 2 3)

(def moj-vektor (vector 1 2 3))
(moj-vektor 1)

(vec '(1 2 3))
(get ["first" "second" "third"] 1)
(peek [1 2 3])
(vector? [1 2 3])
(conj [1 2 3] 4 5)
(assoc [1 2 3] 1 "new value")
(pop [1 2 3])
(subvec [1 2 3 4 5] 2)
(subvec [1 2 3 4 5] 2 4)
;;Maps
(def my-map {:a 1 :b 2 :c 3})
(def my-map {:a 1, :b 2, :c 3})
(my-map :a)
(hash-map :a 1, :b 2, :c 3)
(sorted-map :a 1, :b 2, :c 3)
;;Struct Maps
(defstruct person :first-name :last-name)
(def person1 (struct-map person :first-name "Luke" :last-name "VanderHart"))
(def person2 (struct-map person :first-name "John" :last-name "Smith"))
(person1 :first-name)
(def get-first-name (accessor person :first-name))
(get-first-name person1)

;;Maps As Objects
(assoc {:a 1 :b 2} :c 3)
(assoc {:a 1 :b 2} :c 3 :d 4)
(dissoc {:a 1 :b 2 :c 3} :c)
(dissoc {:a 1 :b 2 :c 3 :d 4} :a :c)
(conj {:a 1 :b 2 :c 3} {:d 4})
(conj {:a 1 :b 2 :c 3} [:d 4])
(merge {:a 1 :b 2} {:c 3 :d 4})
(merge-with + {:a 1 :b 2} {:b 3 :c 4})
(get {:a 1 :b 2 :c 3} :a)
(get {:a 1 :b 2 :c 3} :d 0)
(contains? {:a 1 :b 2 :c 3} :a)
(map? {:a 1 :b 2 :c 3})
(keys {:a 1 :b 2 :c 3})
(vals {:a 1 :b 2 :c 3})

;;Sets
(def languages #{:java :lisp :c++})
(def languages-names #{"Java" "Lisp" "C++"})
(def set1 (hash-set :a :b :c))
(def set2 (sorted-set :a :b :c))
(set1 :a)
(set1 :z)
(clojure.set/union #{:a :b} #{:c :d})
(clojure.set/intersection #{:a :b :c :d} #{:c :d :f :g})
(clojure.set/difference #{:a :b :c :d} #{:c :d :e})

;;What Are Sequences?
(def mylist '(1 2 3))
(first mylist)
(def myvec [1 2 3])
(first myvec)
(def myset #{1 2 3})
(first myset)
(def mymap {:a 1 :b 2 :c 3})
(first mymap)
(rest mylist)

(defn printall [s]
  (if (not (empty? s))
    (do
      (println (str "Item: " (first s)))
      (recur (rest s)))))
(printall mylist)
(printall ["vector" "of" "strings"])
(printall " Hello")

(cons 4 '(1 2 3))
(conj '(1 2 3) 4)
(conj '(1 2 3) 4 5 6)

(map
  (fn [x] (* x x))
  '(1 2 3 4 5 6 7))

(defn square [x]
  (do
    (println (str "Processing: " x))
    (* x x)))
(map square '(1 2 3 4 5 6))
(def map-result (map square '(1 2 3 4 5)))
(nth map-result 2)
;;The Sequence API
;;Sequence Creation
(seq [1 2 3 4 5])
(seq {:a 1 :b 2 :c 3})
(vals {:key1 "value1" :key2 "value2" :key3 "value3"})
(keys {:key1 "value1" :key2 "value2" :key3 "value3"})
(rseq [1 2 3 4])
(take 5 (repeatedly (fn [] "hello")))
(take 5 (repeatedly (fn [] (rand-int 5))))
(take 10 (iterate inc 5))
(take 5 (repeat "hi"))
(repeat 5 "hi")
(range 5)

(split-at 2 [:a :b :c :d :e :f])
(split-with pos? [2 1 5 -3 6 -2 -1])
(partition 2 [:a :b :c :d :e :f])
(partition 2 1 [:a :b :c :d :e :f])
(map pos? [2 1 5 -3 6 -2 -1])
(map + [2 4 8] [1 3 5])
(first [1 2 3 4])
(second [1 2 3 4])
(nth [:a :b :c :d] 2)
(last [1 2 3 4])
(reduce + [1 2 3 4 5])
(apply + 1 [2 3])
(apply + (range 1 6))
(empty? [1 2 3 4])
(empty? [])
(some (fn [n] (< n 5)) [6 9 7 3])
(some (fn [n] (< n 5)) [6 9 7 5])
(every? (fn [n] (< n 5)) [2 1 4 3])
(every? (fn [n] (< n 5)) [2 1 5 3])

(def result (map println (range 1 5)))
(dorun result)
(doall result)

;;STM
(def my-ref (ref 5))
(deref my-ref)
@my-ref
(+ 1 @my-ref)
(dosync (alter my-ref + 3))

;;Bank Accounts in STM
(def account1 (ref 1000))
(def account2 (ref 1500))

(defn transfer "transfers amount of money from a to b" [a b amount]
  (dosync
    (alter a - amount)
    (alter b + amount)))

(transfer account1 account2 300)
(transfer account2 account1 50)

(println "Account #1:" @account1)
(println "Account #2:" @account2)

;;An Address Book in STM
(def my-contacts (ref []))

(defn add-contact "adds a contact to the provided contact list" [contacts contact]
  (dosync
    (alter contacts conj (ref contact))))

(defn print-contacts "prints a list of contacts" [contacts]
  (doseq [c @contacts]
    (println (str "Name: " (@c :lname) ", " (@c :fname)))))

(add-contact my-contacts {:fname "Luke" :lname "VanderHart"})
(add-contact my-contacts {:fname "Stuart" :lname "Sierra"})
(add-contact my-contacts {:fname "John" :lname "Doe"})

(print-contacts my-contacts)

;;Adding Initials to the Address Book
(defn add-initials "adds initials to a single contact and returns it" [contact]
  (assoc contact :initials
         (str (first (contact :fname)) (first (contact :lname)))))

(defn add-all-initials "adds initials to each of the contacts in a list of contacts" [contacts]
  (dosync
    (doseq [contact (ensure contacts)]
      (alter contact add-initials))))

(defn print-contacts-and-initials "prints a list of contacts, with initials" [contacts]
  (dorun (map (fn [c]
                (println (str "Name: " (c :lname) ", " (c :fname) " (" (c :initials) ")"))) @contacts)))

(defn print-contacts-and-initials "prints a list of contacts, with initials" [contacts]
  (doseq [c @contacts]
    (println (str "Name: " (@c :lname) ", " (@c :fname) " (" (@c :initials) ")"))))

(add-all-initials my-contacts)
(print-contacts-and-initials my-contacts)






