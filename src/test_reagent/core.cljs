(ns test-reagent.core
  (:require [reagent.core :as reagent :refer [atom]]
            [goog.string :as gstring]))

(enable-console-print!)

(println "This text is printed from src/test-reagent/core.cljs. Go ahead and edit it and see reloading in action.")

;; define your app data so that it doesn't get over-written on reload

(defonce app-state (atom {:text "Hello world!"}))
(def app-text (reagent/atom ""))
(def input-text (reagent/atom "Enter text...."))
(def click-count (reagent/atom 0))





(defn transform-helper [expr]
  (let [operator (first expr) operant1 (first (rest expr)) operant2 (second (rest expr))]
        (cond (and (expr? operant1) (expr? operant2)) (str "(" (transform-helper operant1) operator (transform-helper operant2) ")")
                   (expr? operant1) (str "(" (transform-helper operant1) operator operant2 ")")
                   (expr? operant2) (str "(" operant1 operator (transform-helper operant2) ")")
                :else (str "(" operant1 operator operant2 ")")    
                    )    
    )
  )


(defn transform-helper [expr]
  "helper function that transforms parts for the expression to MathML"
  (let [operator (first expr) operant1 (first (rest expr)) operant2 (second (rest expr))]
    

  )
)

(defn transform [expr]
  "Removes first and last bracket"
  (let [transformed-expr (transform-helper expr)]
    [:math transformed-expr]))

(defn convert-expr-to-mathml [exp]
  "converts clojure/lisp expression to MathML notation"
  )

(defn counting-component []
  [:div
   [:input {:type "text"
            :value @input-text
            :on-change #(reset! input-text (-> % .-target .-value))}]
   [:input {:type "button" :value "Click me!"
            :on-click #(reset! app-text [:math {:xmlns "http://www.w3.org/1998/Math/MathML"}
                                          [:mi "a"]
                                          [:mo (gstring/unescapeEntities "&InvisibleTimes;")]
                                          [:msup
                                           [:mi "x"]
                                           [:mn "2"]]])}]   
   [:p @app-text]])
      
(reagent/render-component [counting-component]
                          (. js/document (getElementById "app")))

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)


