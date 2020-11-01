export default {
  namespaced: true,
  state: {
    cart: [],
  },
  mutations: {
    pushProductToCart(state, product) {
      product.deleted = false;
      state.cart.push(product);
    },
    popProductToCart(state, product) {
      //const itemToFind = state.cart.find((item) => item.id === product.id)
      //const idx = state.cart.indexOf(i)
      const idx = state.cart.findIndex((item) => item.id === product.id);
      console.log("idx:%d", idx);
      if (idx > -1) state.cart.splice(idx, 1);
    },
    clearAllCart(state) {
      state.cart = [];
    },
  },
  actions: {
    addProductToCart({ state, commit }, product) {
      return new Promise((resolve, reject) => {
        try {
          // cart가 비어있는지 우선 확인
          if (typeof state.cart !== "undefined" && state.cart.length > 0) {
            // cart가 비어있지 않은 경우, 내부에 담긴 상품들과 추가하려는 상품의 매장 정보가 같은지 확인
            const storeId = state.cart[0].storeId;

            if (storeId == product.storeId) {
              // 같다면 cart내부에 같은 상품이 담겨있는지 확인
              const cartItem = state.cart.find(
                (item) => item.id === product.id
              );
              console.log(cartItem);

              // 같은 상품이 존재할 경우 합쳐서 기존 상품은 삭제하고 새 상품을 다시 push
              // action에서는 state를 직접 수정하는 것을 지양하기 때문에 삭제->추가 형식으로 해결했습니다.
              if (cartItem) {
                console.log("같은 상품이 있어!");
                product.amount += cartItem.amount;
                product.totalPrice += cartItem.totalPrice;
                commit("popProductToCart", cartItem);
                commit("pushProductToCart", product);
              } else commit("pushProductToCart", product);
            }

            // 같지 않은 경우 error를 반환(밖에서 예외처리 하도록 함)
            else {
              throw new Error("fail");
            }
          } else {
            // 비어있는 카트에는 별다른 행동 없이 바로 상품을 추가합니다.
            commit("pushProductToCart", product);
          }
          resolve();
        } catch (error) {
          reject(error);
        }
      });
    },
    deleteProductToCart({ state, commit }, product) {
      const cartItem = state.cart.find((item) => item.id === product.id);
      if (cartItem) {
        commit("popProductToCart", product);
      }
    },
    clearCart({ state, commit }) {
      if (state.cart.length > 0) {
        commit("clearAllCart");
      }
    },
  },

  getters: {},
};
