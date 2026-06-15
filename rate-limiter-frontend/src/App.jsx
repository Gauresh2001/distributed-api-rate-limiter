import { useEffect, useState } from "react";
import api from "./services/api";
import "./App.css";

function App() {
  const [rules, setRules] = useState([]);
  const [products, setProducts] = useState([]);
  const [headers, setHeaders] = useState({});
  const [message, setMessage] = useState("");

  const [form, setForm] = useState({
    endpoint: "/api/products",
    limit: "",
    timeWindowInSeconds: "",
    userType: "FREE",
  });

  const [apiKey, setApiKey] = useState("gauresh-free-key");
  const [userType, setUserType] = useState("FREE");

  const loadRules = async () => {
    try {
      const res = await api.get("/rate-limit/config");
      setRules(res.data);
    } catch {
      setMessage("Failed to load rate limit rules");
    }
  };

  useEffect(() => {
    loadRules();
  }, []);

  const handleChange = (e) => {
    setForm({
      ...form,
      [e.target.name]: e.target.value,
    });
  };

  const createRule = async (e) => {
    e.preventDefault();

    try {
      await api.post("/rate-limit/config", {
        endpoint: form.endpoint,
        limit: Number(form.limit),
        timeWindowInSeconds: Number(form.timeWindowInSeconds),
        userType: form.userType,
      });

      setMessage("Rate limit rule created successfully");
      setForm({
        endpoint: "/api/products",
        limit: "",
        timeWindowInSeconds: "",
        userType: "FREE",
      });

      loadRules();
    } catch {
      setMessage("Failed to create rule");
    }
  };

  const deleteRule = async (id) => {
    try {
      await api.delete(`/rate-limit/config/${id}`);
      setMessage("Rule deleted successfully");
      loadRules();
    } catch {
      setMessage("Failed to delete rule");
    }
  };

  const testProductsApi = async () => {
    try {
      const res = await api.get("/api/products", {
        headers: {
          "X-API-KEY": apiKey,
          "X-USER-TYPE": userType,
        },
      });

      setProducts(res.data);
      setHeaders({
        limit: res.headers["x-ratelimit-limit"],
        remaining: res.headers["x-ratelimit-remaining"],
        reset: res.headers["x-ratelimit-reset"],
      });

      setMessage("API request successful");
    } catch (error) {
      setProducts([]);

      if (error.response) {
        setMessage(error.response.data.message || "Rate limit exceeded");
        setHeaders({
          limit: error.response.headers["x-ratelimit-limit"],
          remaining: error.response.headers["x-ratelimit-remaining"],
          reset: error.response.headers["x-ratelimit-reset"],
        });
      } else {
        setMessage("Backend server not running");
      }
    }
  };

  return (
    <div className="app">
      <h1>Distributed API Rate Limiter</h1>
      <p className="subtitle">Spring Boot + Redis + MySQL Rate Limiting Dashboard</p>

      {message && <div className="message">{message}</div>}

      <div className="grid">
        <section className="card">
          <h2>Create Rate Limit Rule</h2>

          <form onSubmit={createRule}>
            <label>Endpoint</label>
            <input
              name="endpoint"
              value={form.endpoint}
              onChange={handleChange}
              placeholder="/api/products"
              required
            />

            <label>Limit</label>
            <input
              name="limit"
              type="number"
              value={form.limit}
              onChange={handleChange}
              placeholder="5"
              required
            />

            <label>Time Window Seconds</label>
            <input
              name="timeWindowInSeconds"
              type="number"
              value={form.timeWindowInSeconds}
              onChange={handleChange}
              placeholder="60"
              required
            />

            <label>User Type</label>
            <select name="userType" value={form.userType} onChange={handleChange}>
              <option value="FREE">FREE</option>
              <option value="PREMIUM">PREMIUM</option>
              <option value="ADMIN">ADMIN</option>
            </select>

            <button type="submit">Create Rule</button>
          </form>
        </section>

        <section className="card">
          <h2>Test Protected API</h2>

          <label>API Key</label>
          <input value={apiKey} onChange={(e) => setApiKey(e.target.value)} />

          <label>User Type</label>
          <select value={userType} onChange={(e) => setUserType(e.target.value)}>
            <option value="FREE">FREE</option>
            <option value="PREMIUM">PREMIUM</option>
            <option value="ADMIN">ADMIN</option>
          </select>

          <button onClick={testProductsApi}>Send Request</button>

          <div className="headers-box">
            <p><b>Limit:</b> {headers.limit || "-"}</p>
            <p><b>Remaining:</b> {headers.remaining || "-"}</p>
            <p><b>Reset:</b> {headers.reset || "-"}</p>
          </div>
        </section>
      </div>

      <section className="card">
        <h2>Rate Limit Rules</h2>

        <div className="table-wrap">
          <table>
            <thead>
              <tr>
                <th>ID</th>
                <th>Endpoint</th>
                <th>Limit</th>
                <th>Window</th>
                <th>User Type</th>
                <th>Action</th>
              </tr>
            </thead>

            <tbody>
              {rules.map((rule) => (
                <tr key={rule.id}>
                  <td>{rule.id}</td>
                  <td>{rule.endpoint}</td>
                  <td>{rule.limit}</td>
                  <td>{rule.timeWindowInSeconds}s</td>
                  <td>{rule.userType}</td>
                  <td>
                    <button className="delete-btn" onClick={() => deleteRule(rule.id)}>
                      Delete
                    </button>
                  </td>
                </tr>
              ))}

              {rules.length === 0 && (
                <tr>
                  <td colSpan="6">No rules found</td>
                </tr>
              )}
            </tbody>
          </table>
        </div>
      </section>

      <section className="card">
        <h2>Products API Response</h2>

        <div className="products">
          {products.map((p) => (
            <div className="product" key={p.id}>
              <h3>{p.name}</h3>
              <p>₹{p.price}</p>
            </div>
          ))}

          {products.length === 0 && <p>No product data</p>}
        </div>
      </section>
    </div>
  );
}

export default App;