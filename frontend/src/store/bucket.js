export default {
  namespaced: true,
  state: {
    cart: [],
  },
  mutations: {
    pushProductToCart(state, product) {
      state.cart.push(product);
      product.deleted = false;
    },
    popProductToCart(state, productId) {
      //const itemToFind = state.cart.find((item) => item.id === product.id)
      //const idx = state.cart.indexOf(i)
      const idx = state.cart.findIndex((item) => item.id === productId);
      if (idx > -1) state.cart.splice(idx, 1);
    },
    clearAllCart(state) {
      state.cart = [];
    },
  },
  actions: {
    addProductToCart({ state, commit }, product) {
      const storeId = state.cart[0].storeId;
      if (storeId == product.storeId) {
        commit("pushProductToCart", product);
      }
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
