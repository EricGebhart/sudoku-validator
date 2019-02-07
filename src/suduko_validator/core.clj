(ns suduko-validator.core
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))


;;; shouldn't actually need the check for length...
(defn unique-nine?
  "Take a string characters and determine there is only one of each with a length of 9"
  [coll]
  (let [cnt (count col)
        cntd (count (distinct coll))]
    (and (= cnt 9)
         (= cnt cntd))))

(defn one-thru-nine?
  "take a string of characters and determine if they are just 1-9"
  [s]
  (= s (re-matches #"^[1-9]*$" s)))

(defn validate-sets-of-nine
  "validate suduko sets of 9 squares"
  [coll]
  (->> coll
       (map unique-nine?)
       (reduce #(and % %2) true)))

(defn get-rows
  "get the suduko rows"
  [s]
  (partition 9 s))

(defn validate-rows
  "validate the suduko rows from a string of numbers"
  [s]
  (validate-sets-of-nine (get-rows s)))

(defn get-columns
  "create suduko columns from a string of concatenated rows"
  [s]
  (map #(take-nth 9 %)
       (map #(drop % s) (range 0 9))))

(defn validate-columns
  "validate the suduko columns from a string of concatenated rows"
  [s]
  (validate-sets-of-nine (get-columns s)))

(defn get-grid-row
  "get the grid-row in position 0-8"
  [row pos]
  (take 3 (drop (* pos 3) row )))

(defn get-grid
  "get all the rows of 3 for a given grid position"
  [rowgroup pos]
  (mapcat #(get-grid-row % pos) rowgroup))

(defn get-row-group-grids
  "create lists of the 9 numbers in each of the three grids in a group of three rows."
  [rg]
  (map #(get-grid rg %) (range 0 3)))

(defn get-grids
  [s]
  (mapcat get-row-group-grids (partition 3 (get-rows s))))

(defn validate-grids
  "create and validate the 9 grid of numbers."
  [s]
  (validate-sets-of-nine (get-grids s)))

(defn validate
  "validate that a string of 81 characters is a valid suduko solution."
  [s]
  (when (and (= 81 (count s))
             (one-thru-nine? s)
             (validate-rows s)
             (validate-columns s)
             (validate-grids s))
    true))
