
import axios from 'axios'
import data from "bootstrap/js/src/dom/data.js";

/// Типы фотосессий
export const listSessionTypes   = () => axios.get('/session-types')
export const createSessionType  = data => axios.post('/session-types', data)
export const updateSessionType  = (id, data) => axios.put(`/session-types/${id}`, data)
export const deleteSessionType  = id => axios.delete(`/session-types/${id}`)
// Сессии
export const listSessions      = () => axios.get('/sessions')
export const getSessionById    = (id) => axios.get(`/sessions/${id}`)
export const createSession     = (data) => axios.post('/sessions', data)

// Фотографии
export const uploadSessionPhotos = (sessionId, files) => {
  const form = new FormData()
  Array.from(files).forEach(f => form.append('files', f))
  return axios.post(`/sessions/${sessionId}/photos`, form, {
    headers: { 'Content-Type': 'multipart/form-data' }
  })
}
// Удаление одного фото по имени и id сессии
export const deletePhoto = (sessionId, filename) =>
  axios.delete(`/sessions/${sessionId}/photos/${filename}`);

export const deleteSession = (sessionId) =>
  axios.delete(`/sessions/${sessionId}`)
