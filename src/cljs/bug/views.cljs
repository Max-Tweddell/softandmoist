(ns bug.views
  (:require [reagent.core :as r]
            [re-frame.core :as rf]
            [clojure.string :as str]))

(defn store-item [title description]
  [:div.item
    [:h2 title
     [:p description]]])

(defn store-app []
  (store-item "egg" "a vegan egg"))

;; cart

(defn cart-item []
  (let [editing (r/atom false)]
    (fn [{:keys [id quantity title]}]
      [:li
       [:div.view
        [:input.toggle
         {:type "number"
          :on-change #(rf/dispatch)}]]])))


;; main

(defn nav-link [uri title page collapsed?]
  (let [selected-page (rf/subscribe [:page])]
    [:li.nav-item
     {:class (when (= page @selected-page) "active")}
     [:a.nav-link
      {:href uri
       :on-click #(reset! collapsed? true)} title]]))

(defn navbar []
  (r/with-let [collapsed? (r/atom true)]
    [:nav.navbar.navbar-dark.bg-primary
     [:button.navbar-toggler.hidden-sm-up
      {:on-click #(swap! collapsed? not)} "☰"]
     [:div.collapse.navbar-toggleable-xs
      (when-not @collapsed? {:class "in"})
      [:a.navbar-brand {:href "#/"} "bug"]
      [:ul.nav.navbar-nav
       [nav-link "#/" "Home" :home collapsed?]
       [nav-link "#/about" "About" :about collapsed?]
       [nav-link "#/cart" "Cart" :cart collapsed?]]]]))

(defn about-page []
  [:div.container
   [:div.row
    [:div.col-md-12
     [:img {:src (str js/context "/img/warning_clojure.png")}]]]])

(defn home-page []
  [:div.container
   [:div.row>div.col-sm-12
    [:div [:p "hellooo"
           ]]]])



(defn cart-app []
  (cart-item))
