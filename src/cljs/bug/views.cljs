(ns bug.views
  (:require [reagent.core :as r]
            [re-frame.core :refer [subscribe dispatch]]
            [clojure.string :as str]))

(defn store-item [title description]
  [:div.item
    [:h2 title
     [:p description]]])

(defn store-app []
  (store-item "egg" "a vegan egg"))

(defn cart-item []
  (let [editing (r/atom false)]
    (fn [{:keys [id quantity title]}]
      [:li
       [:div.view
        [:input.toggle
         {:type "number"
          :on-change #(dispatch)}]]])))

(defn cart-app []
  (cart-item))
