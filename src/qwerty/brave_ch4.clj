(ns qwerty.brave-ch4
  (use clojure.repl))

(def human-consumption [8.1 7.3 6.6 5.0])
(def critter-consumption [0.0 0.2 0.3 1.1])
(defn unify-diet-data
  [human critter]
  {:human human
   :critter critter})

(map unify-diet-data human-consumption critter-consumption)

(def sum #(reduce + %))
(def avg #(/ (sum %) (count %)))
(defn stats
  [numbers]
  (map #(% numbers) [sum count avg]))

(stats [3 4 10])

(reduce (fn [new-map [key val]]
          (assoc new-map key (inc val)))
        {}
        {:max 30 :min 10})

(assoc (assoc {} :max (inc 30))
  :min (inc 10))

(reduce (fn [new-map [key val]]
          (if (> val 4)
            (assoc new-map key val)
            new-map))
        {}
        {:human   4.1
         :critter 3.9})

(defn my-map [f coll]
  "Implemented map by using reduce"
  (seq (reduce (fn [new-seq elem]
                 (conj new-seq (f elem)))
               [] coll)))

(my-map inc human-consumption)

(take 3 [1 2 3 4 5 6 7 8 9 10])

(drop 3 [1 2 3 4 5 6 7 8 9 10])

(def food-journal
  [{:month 1 :day 1 :human 5.3 :critter 2.3}
   {:month 1 :day 2 :human 5.1 :critter 2.0}
   {:month 2 :day 1 :human 4.9 :critter 2.1}
   {:month 2 :day 2 :human 5.0 :critter 2.5}
   {:month 3 :day 1 :human 4.2 :critter 3.3}
   {:month 3 :day 2 :human 4.0 :critter 3.8}
   {:month 4 :day 1 :human 3.7 :critter 3.9}
   {:month 4 :day 2 :human 3.7 :critter 3.6}])

(take-while #(< (:month %) 3) food-journal)

(drop-while #(< (:month %) 3) food-journal)

(take-while #(< (:month %) 4)
  (drop-while #(< (:month %) 2) food-journal))

(and nil "ovaj string nece biti vracen")

(and false "ovaj string nece biti vracen")

(and true "ovaj string je vracen")

(and "some value" "ovaj string je vracen")

(and :asdf "ovaj string je vracen")

(some #(and (> (:critter %) 3) %) food-journal)

(sort [3 1 2])

(sort ["aaa" "c" "bb"])

(sort-by count ["aaa" "c" "bb"])

(concat [1 2] [3 4])

(def vampire-database
  {0 {:makes-blood-puns? false, :has-pulse? true  :name "McFishwich"}
   1 {:makes-blood-puns? false, :has-pulse? true  :name "McMackson"}
   2 {:makes-blood-puns? true,  :has-pulse? false :name "Damon Salvatore"}
   3 {:makes-blood-puns? true,  :has-pulse? true  :name "Mickey Mouse"}})

(defn vampire-related-details
  [social-security-number]
  (Thread/sleep 1000)
  (get vampire-database social-security-number))

(time (vampire-related-details 0))

(defn vampire?
  [record]
  (and (:makes-blood-puns? record)
       (not (:has-pulse? record))
       record))

(defn identify-vampire
  [social-security-numbers]
  (first (filter vampire?
                 (map vampire-related-details social-security-numbers))))

(time (identify-vampire [0 1 2 3]))

(time (def mapped-details (map vampire-related-details (range 0 1000000))))

(time (first mapped-details))

(time (first mapped-details))

(time (identify-vampire (range 0 1000000)))

(nth (repeat "na") 10)

(take 15 (repeatedly (fn [] (rand 10))))

(cons 0 '(2 4 6))

(defn even-numbers
  ([] (even-numbers 0))
  ([n] (cons n (lazy-seq (even-numbers (+ n 2))))))

(take 10 (even-numbers))

(time (nth (even-numbers) 20000000))

(empty [])

(empty? '())

(empty? (even-numbers))

(count '(0 1 * + 7))

(every? number? [1 2 5 4 ""])

(every? number? (even-numbers))

(map identity {:sunlight-reaction "Glitter!", :b "Ccc"})

(into {} (map identity {:sunlight-reaction "Glitter!", :b "Ccc"}))

(into {:favorite-emotion "gloomy"} [[:sunlight-reaction "Glitter!"]])

(into ["cherry"] '("pine" "spruce"))

(into {:favorite-animal "kitty"} {:least-favorite-smell "dog"
                                  :relationship-with-teenager "creepy"})

(conj [0] [1 2 3])

(conj [0] 1)

(conj [0] 1 2 3 4 5)

(conj {:time "midnight"} {:place "ye olde cemetarium" :who "somebody"})

(defn my-conj
  [target & additions]
  (into target additions))

(my-conj [0] 1 2 3)

(max 0 1 2)

(max [0 1 2])

(apply max [0 1 2])

(defn my-into
  [target additions]
  (apply conj target additions))

(apply conj [0] [1 2 3 4 5])

(def add10 (partial + 10))
(add10 3)

(add10 5)

(def add-missing-elements
  (partial conj ["water" "earth" "air"]))

(add-missing-elements "unobtainium" "adamantium")

(defn my-partial
  [partialized-fn & args]
  (fn [& more-args]
    (apply partialized-fn (into args more-args))))

